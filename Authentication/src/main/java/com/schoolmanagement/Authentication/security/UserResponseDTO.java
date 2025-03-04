package com.schoolmanagement.Authentication.security;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter

public class UserResponseDTO {
    private String username;
    private List<String> roles;

    public UserResponseDTO(String username,List<String> roles) {
        this.username = username;
        this.roles = roles;

    }

}
