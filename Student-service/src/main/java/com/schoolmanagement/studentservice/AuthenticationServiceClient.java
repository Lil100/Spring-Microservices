package com.schoolmanagement.studentservice;


import com.schoolmanagement.studentservice.dto.UserTokenResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "authentication", url = "http://localhost:8082") // Use Authentication Service URL
public interface AuthenticationServiceClient {

    @GetMapping("/auth/validate")
    ResponseEntity<UserTokenResponseDTO> validateToken( String token);
}



