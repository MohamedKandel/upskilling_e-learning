 package com.example.elearning.Security;

import com.example.elearning.Models.User;
import com.example.elearning.Repos.UserRepo;
import com.example.elearning.Services.JavaAuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // Dependency injection
    JavaAuthService _AuthService;
    UserRepo _UserRepo;

    public JwtAuthenticationFilter(
            JavaAuthService _AuthService,
            UserRepo _UserRepo) {

        this._AuthService = _AuthService;
        this._UserRepo = _UserRepo;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        // No JWT token -> continue normally
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);

        try {

            String email = _AuthService.extractSpecificClaim(
                    token,
                    "email",
                    String.class
            );

            if (email != null
                    && SecurityContextHolder.getContext().getAuthentication() == null
                    && !_AuthService.isTokenExpired(token)) {

                Optional<User> userAccount =
                        _UserRepo.findByEmail(email);

                if (userAccount.isPresent()) {

                    User userAcc = userAccount.get();

                    // Giving authority based on the user's role
                    List<GrantedAuthority> authorities =
                            List.of(
                                    new SimpleGrantedAuthority(
                                            "ROLE_" + userAcc.getRole()
                                    )
                            );

                    // User is authenticated
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userAcc,
                                    null,
                                    authorities
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

        } catch (Exception ex) {

            // Invalid JWT -> don't authenticate the user.
            // Let Spring Security decide whether authentication is required.
            SecurityContextHolder.clearContext();
        }

        // ALWAYS continue the request
        filterChain.doFilter(request, response);
    }
}
