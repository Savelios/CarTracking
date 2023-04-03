package com.example.demo.backend.repository;


import com.example.demo.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findFirstByUsername(String username);
    Optional<User> findFirstByEmail(String email);
    Optional<User> findFirstByPhone(String phone);
    Optional<User> findFirstByUsernameAndPassword(String username, String password);
}