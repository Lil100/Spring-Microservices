package com.schoolmanagement.Authentication;

import com.schoolmanagement.Authentication.security.JwtUtil;
import com.schoolmanagement.Authentication.security.UserRegistrationDTO;
import com.schoolmanagement.Authentication.security.UserSigninDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

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
    @PostMapping("/signin")
    public ResponseEntity<Map<String, String>> signIn(@RequestBody UserSigninDTO usersigninDTO) {
        try {
            // Extract username and password from the DTO
            String username = usersigninDTO.getUsername();
            String password = usersigninDTO.getPassword();

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            // If authentication is successful, generate JWT token
            String token = jwtUtil.generateToken(username);

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

}

