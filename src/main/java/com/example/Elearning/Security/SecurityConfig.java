package com.example.Elearning.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthenticationFilter
    ) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http

            // JWT Authentication
            .csrf(csrf -> csrf.disable())

            // No Session
            .sessionManagement(session ->
                session.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
                )
            )

            // Authorization
            .authorizeHttpRequests(auth -> auth

                // Authentication endpoints
                .requestMatchers("/api/auth/**")
                .permitAll()

                // Student endpoints
                .requestMatchers("/api/student/**")
                .hasRole("STUDENT")

                // Instructor endpoints
                .requestMatchers("/api/instructor/**")
                .hasRole("INSTRUCTOR")

                // Any other endpoint
                .anyRequest()
                .authenticated()
            )

            // JWT Filter
            .addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }
}