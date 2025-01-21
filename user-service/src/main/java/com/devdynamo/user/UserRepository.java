package com.devdynamo.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devdynamo.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
