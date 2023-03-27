package com.example.demo.backend.service.servant;

import com.example.demo.backend.domain.Location;
import com.example.demo.backend.repository.LocationRepository;

public class LocationServant {
    private LocationRepository repository;

    public LocationServant(LocationRepository repository) {
        this.repository = repository;
    }

//    public Location getLatestLocation() {
//        return repository.findAll();
//    }

    public void saveLocation(Location location) {
        repository.save(location);
    }
}

