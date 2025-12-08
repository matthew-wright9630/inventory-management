package com.skillstorm.inventory_management_backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillstorm.inventory_management_backend.models.Location;
import com.skillstorm.inventory_management_backend.models.Warehouse;
import com.skillstorm.inventory_management_backend.repositories.WarehouseRepository;
import com.skillstorm.inventory_management_backend.validators.WarehouseValidator;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final LocationService locationService;

    public WarehouseService(WarehouseRepository warehouseRepository,
            LocationService locationService) {
        this.warehouseRepository = warehouseRepository;
        this.locationService = locationService;
    }

    public List<Warehouse> findAllWarehouses() {
        return warehouseRepository.findAll();
    }

    public Warehouse findWarehouseById(int id) {
        Optional<Warehouse> warehouse = warehouseRepository.findById(id);

        if (warehouse.isPresent()) {
            return warehouse.get();
        }
        throw new IllegalArgumentException("Warehouse does not exist. Please try with another warehouse.");
    }

    public Warehouse createWarehouse(Warehouse warehouse, int locationId) {
        Location location = locationService.findLocationById(locationId);
        if (location.getId() <= 0) {
            throw new IllegalArgumentException("Location does not exist. Please try with another location.");
        }
        warehouse.setLocation(location);
        if (WarehouseValidator.validateWarehouse(warehouse)) {
            return warehouseRepository.save(warehouse);
        }
        throw new IllegalArgumentException("Values were not input as expected. input: " + warehouse);

    }

    public List<Warehouse> findWarehousesByCapacityLeft(int capacityPercent) {
        List<Warehouse> warehouses = warehouseRepository.findAll();
        List<Warehouse> returnedWarehouses = new ArrayList<Warehouse>();
        for (Warehouse w : warehouses) {
            returnedWarehouses.add(w);
            // find all storage_bin where w.id == warehouse.id
            // if storageBin.length >= capacityPercent, returnedWarehouses.add(w);
        }
        return returnedWarehouses;
    }

    public Warehouse saveWarehouse(Warehouse warehouse, int locationId) {

        Location location = locationService.findLocationById(locationId);
        if (location.getId() <= 0) {
            throw new IllegalArgumentException("Location does not exist. Please try with another location.");
        }
        if (warehouse.getId() > 0) {
            warehouse.setLocation(location);
            WarehouseValidator.validateWarehouse(warehouse);
            warehouseRepository.save(warehouse);
            return warehouse;
        }
        throw new IllegalArgumentException("Warehouse does not exist. Please try with another warehouse.");
    }

    public Warehouse deleteWarehouse(int id) {
        Warehouse foundLocation = findWarehouseById(id);
        if (foundLocation.getId() > 0) {
            warehouseRepository.deleteWarehouse(foundLocation.getId(), false);
            return foundLocation;
        }
        throw new IllegalArgumentException("Warehouse does not exist. Please try with another location.");
    }
}
