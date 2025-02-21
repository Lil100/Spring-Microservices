package com.schoolmanagement.Authentication;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    // Define Role enum inside the UserEntity class and implement GrantedAuthority
    public enum Role implements GrantedAuthority {
        STUDENT, TEACHER, ADMIN;

        @Override
        public String getAuthority() {
            return name();  // Return the role name (STUDENT, TEACHER, ADMIN) as the authority
        }
    }

    // The role field for the UserEntity
    @Enumerated(EnumType.STRING)
    private Role role;

    // Implementing the getAuthorities method
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Set.of(role);  // Return the role as a set of GrantedAuthorities
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
