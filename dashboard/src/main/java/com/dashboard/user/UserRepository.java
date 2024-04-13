package com.dashboard.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Integer id);
    Optional<User> findByEmail(String email);
    Optional<User> findOneByEmailAndPassword(String email, String password);

}
