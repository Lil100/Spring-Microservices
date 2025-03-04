package com.schoolmanagement.Authentication;

import com.schoolmanagement.Authentication.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;  // JwtUtil should be properly injected here

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;  // Correctly injected JwtUtil here
    }

    // Sign-up method (only for TEACHER or ADMIN roles)
    public UserEntity signUp(String username, String password, UserEntity.Role role) {
        if (role == UserEntity.Role.STUDENT) {
            throw new IllegalArgumentException("Students cannot sign up. Only teachers and admins can register.");
        }

        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username already exists.");
        }

        // Encode password
        String encodedPassword = passwordEncoder.encode(password);

        // Create new user
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(encodedPassword);
        userEntity.setRole(role);

        // Save to database
        return userRepository.save(userEntity);
    }

    // Sign-in method (for all roles)
    public String signIn(String username, String password) {
        UserEntity userEntity = userRepository.findByUsername(username);

        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // Validate password
        if (!passwordEncoder.matches(password, userEntity.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }
        List<UserEntity.Role> roles = List.of(userEntity.getRole());

        // Generate JWT token
        return jwtUtil.generateToken(username, roles);
    }
}
