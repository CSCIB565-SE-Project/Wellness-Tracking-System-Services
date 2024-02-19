package com.springjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springjava.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserNameOrEmail(String username, String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
