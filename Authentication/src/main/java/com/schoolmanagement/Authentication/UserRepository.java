package com.schoolmanagement.Authentication;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByUsername(String username);
    UserEntity findByUsername(String username);
}
