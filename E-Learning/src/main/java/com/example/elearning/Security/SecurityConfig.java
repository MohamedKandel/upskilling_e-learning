package com.example.elearning.Security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//Setting Authentication Rules:
@Configuration
@EnableWebSecurity
public class SecurityConfig  {


    JwtAuthenticationFilter _JwtAuthFilter;

    public SecurityConfig() {
    }

    public SecurityConfig(JwtAuthenticationFilter _JwtAuthFilter) {
        this._JwtAuthFilter = _JwtAuthFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();

    }

    @Bean
    public SecurityFilterChain setupChain(HttpSecurity http)
    {
        http.csrf(csrf->csrf.disable())
                .sessionManagement(
                        session -> session
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth
                        -> auth.requestMatchers(

                        "/swagger-resources/**",
                        "/api/auth/**"

                        ).permitAll()
//                         .requestMatchers("/api/instructors/**").hasRole("INSTRUCTOR")

                        .anyRequest().authenticated())
                .addFilterBefore(_JwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }

}
