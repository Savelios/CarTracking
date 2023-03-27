package com.example.demo.backend.repository;

import com.example.demo.backend.domain.Car;
import com.example.demo.backend.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findAll();
    Location save(Location location);
}

