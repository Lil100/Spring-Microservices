package com.schoolmanagement.Authentication.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSigninDTO {
    private String username;
    private String password;
}
