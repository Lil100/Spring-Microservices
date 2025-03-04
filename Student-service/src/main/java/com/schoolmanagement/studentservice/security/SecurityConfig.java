package com.schoolmanagement.studentservice.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;

    public SecurityConfig(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("http://localhost:8083/swagger-ui/index.html#").permitAll()
                        .requestMatchers("/exams/**").permitAll()
                        .requestMatchers("/reports/**").permitAll()
                        .requestMatchers("/students/**").permitAll() // Only teachers, admins, and parents can access student data
                        .requestMatchers("/students/{studentId}").hasAnyAuthority("STUDENT", "TEACHER", "ADMIN") // A student can view their own record
                        .requestMatchers("/students/class/**", "/students/stream/**").hasAnyAuthority("TEACHER", "ADMIN") // Class/Stream-level access
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
