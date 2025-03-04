package com.schoolmanagement.Authentication;

import com.schoolmanagement.Authentication.security.JwtUtil;
import com.schoolmanagement.Authentication.security.UserRegistrationDTO;
import com.schoolmanagement.Authentication.security.UserResponseDTO;
import com.schoolmanagement.Authentication.security.UserSigninDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;

    // Endpoint for Sign-Up (only for Teacher or Admin)
    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> signUp(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        try {
            // Convert role to enum if needed
            UserEntity.Role role = UserEntity.Role.valueOf(userRegistrationDTO.getRole().toUpperCase());

            // Sign-up the user with the received DTO
            UserEntity userEntity = userService.signUp(
                    userRegistrationDTO.getUsername(),
                    userRegistrationDTO.getPassword(), // Use password from DTO
                    role
            );

            // Prepare response data
            Map<String, String> response = new HashMap<>();
            response.put("message", "User registered successfully");
            response.put("username", userEntity.getUsername());
            response.put("role", userEntity.getRole().toString());

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // Handle invalid role exception or user already exists
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }



    // Endpoint for Sign-In (for Teacher, Admin, and Student)
    // Endpoint for Sign-In (for Teacher, Admin, and Student)
    @PostMapping("/signin")
    public ResponseEntity<Map<String, String>> signIn(@RequestBody UserSigninDTO usersigninDTO) {
        try {
            // Extract username and password from the DTO
            String username = usersigninDTO.getUsername();
            String password = usersigninDTO.getPassword();

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            UserEntity userEntity = userRepository.findByUsername(username);

            // Check if user exists
            if (userEntity == null) {
                throw new UsernameNotFoundException("User not found with username: " + username);
            }

            // Get the role as a list
            List<UserEntity.Role> roles = List.of(userEntity.getRole());

            // Generate JWT token with roles
            String token = jwtUtil.generateToken(username, roles);

            // Prepare response data
            Map<String, String> response = new HashMap<>();
            response.put("message", "Sign-in successful");
            response.put("token", token);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException | UsernameNotFoundException e) {
            // Handle invalid credentials or user not found exception
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token) {
        // Remove "Bearer " prefix from the token
        String jwtToken = token.substring(7);

        if (jwtUtil.isTokenValid(jwtToken)) {
            String username = jwtUtil.extractUsername(jwtToken);
            List<UserEntity.Role> roles = jwtUtil.extractRoles(jwtToken);  // Extract roles from the token
            List<String> roleNames = roles.stream().map(Enum::name).collect(Collectors.toList());  // Convert Role enum to string list

            // Return username and roles in the response
            return ResponseEntity.ok(new UserResponseDTO(username, roleNames));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }

}

