package com.schoolmanagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disables CSRF protection explicitly for REST APIs
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/swagger-ui/**",
                                "/webjars/**"
                        ).permitAll()  // Permit all access to Swagger UI and API docs
                        .anyRequest().permitAll()  // Allow all other requests without authentication
                )
                .formLogin(form -> form
                        .permitAll()  // Allow form login without authentication
                )
                .httpBasic().disable();  // Disable HTTP basic authentication

        return http.build();
    }
}
