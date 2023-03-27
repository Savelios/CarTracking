package com.example.demo.backend.service;

import com.example.demo.backend.domain.Location;

import java.util.List;

public interface LocationService {
    void save(Location location);
    List<Location> getAllLocations();
}
