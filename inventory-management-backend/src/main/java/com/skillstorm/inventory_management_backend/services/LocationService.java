package com.skillstorm.inventory_management_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillstorm.inventory_management_backend.models.Location;
import com.skillstorm.inventory_management_backend.repositories.LocationRepository;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> findAllLocations() {
        return locationRepository.findAll();
    }

    public Location findLocationById(int id) throws IllegalArgumentException {
        Optional<Location> location = locationRepository.findById(id);

        if (location.isPresent()) {
            return location.get();
        }
        throw new IllegalArgumentException("Location does not exist. Please try with another location.");
    }

    public Location saveLocation(Location location) {
        locationRepository.save(location);
        return location;
    }

    public Location deleteLocation(Location location) {
        locationRepository.deleteLocation(location.getId(), false);
        return location;
    }
}
