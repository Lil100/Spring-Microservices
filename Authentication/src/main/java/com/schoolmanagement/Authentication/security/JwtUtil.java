package com.schoolmanagement.Authentication.security;

import com.schoolmanagement.Authentication.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "5f6f62d8fb943c1eb48b5a4f7f6349e7643b25e8b6be88b9d7a09c77836dbd70";

    private Key getSigningKey() {
        // Convert the secret key string into a Key object
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)); // Convert string to bytes
    }

    // Generate token, now accepting List<UserEntity.Role> and storing roles as strings
    public String generateToken(String username, List<UserEntity.Role> roles) {
        List<String> roleStrings = roles.stream()
                .map(UserEntity.Role::name)  // Convert enum values to their string names
                .collect(Collectors.toList());  // Collect them into a list of strings

        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roleStrings)  // Store roles as a list of strings
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))  // 1 hour expiration
                .signWith(getSigningKey())
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // Extract roles from token and convert them back to List<UserEntity.Role>
    public List<UserEntity.Role> extractRoles(String token) {
        Claims claims = extractClaims(token);
        List<String> roles = claims.get("roles", List.class);  // Extract roles as List<String>
        return roles.stream()
                .map(UserEntity.Role::valueOf)  // Convert each string back to the corresponding enum value
                .collect(Collectors.toList());  // Collect them into a list of Role enums
    }

    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    // Validate token against username and expiration
    public boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }

    // Check if token is valid (handles invalid format or expired token)
    public boolean isTokenValid(String token) {
        try {
            String username = extractUsername(token);
            return username != null && !isTokenExpired(token);
        } catch (Exception e) {
            return false; // If any exception occurs (e.g., invalid token format), return false
        }
    }
}
