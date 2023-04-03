package com.example.demo.backend.repository;

import com.example.demo.backend.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> searchByRegistrationNumber(String registrationNumber);
    List<Car> searchByBrandAndModelAndRegistrationNumber(String brand, String model, String registrationNumber);
}