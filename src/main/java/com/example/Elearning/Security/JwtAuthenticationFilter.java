package com.example.Elearning.Security;

import java.io.IOException;
import java.util.Optional;

import com.example.Elearning.Models.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.Elearning.Repositories.UserRepository;
import java.util.List;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component 
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService _service;
    private final UserRepository _repo;

    public JwtAuthenticationFilter(JwtService service, UserRepository repo) {
        super();
        this._repo = repo;
        this._service = service;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String header = request.getHeader("Authorization");

            if(header == null || !header.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            String token = header.substring(7);

            String email = _service.extractSpecificClaim(token,
                "email",String.class
            );
            String role = _service.extractSpecificClaim(
        token,
        "role",
        String.class
);

            if(email != null
                && SecurityContextHolder.getContext().getAuthentication() == null) {
                    
                    if(!_service.isTokenExpired(token)) {
                        Optional<User> account = _repo.findByEmail(email);
                        if (account.isPresent()) {

    User myAccount = account.get();

    SimpleGrantedAuthority authority =
        new SimpleGrantedAuthority("ROLE_" + role);

    UsernamePasswordAuthenticationToken authToken =
        new UsernamePasswordAuthenticationToken(
            myAccount,
            null,
            List.of(authority)
        );

    authToken.setDetails(
        new WebAuthenticationDetailsSource()
            .buildDetails(request)
    );

    SecurityContextHolder
        .getContext()
        .setAuthentication(authToken);
}
                    }
                }

            filterChain.doFilter(request, response);
        }catch (Exception e) {
    System.out.println("JWT ERROR: " + e.getMessage());
    e.printStackTrace();

    filterChain.doFilter(request, response);
}
    }

}
