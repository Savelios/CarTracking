package com.example.demo.backend.repository;

import com.example.demo.backend.domain.Car;
import com.example.demo.backend.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Optional<Message> findFirstByType(String type);

}
