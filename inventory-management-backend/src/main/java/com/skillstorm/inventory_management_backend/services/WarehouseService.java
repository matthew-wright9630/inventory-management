package com.skillstorm.inventory_management_backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillstorm.inventory_management_backend.models.Location;
import com.skillstorm.inventory_management_backend.models.Warehouse;
import com.skillstorm.inventory_management_backend.repositories.WarehouseRepository;

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
        return null;
    }

    public Warehouse createWarehouse(Warehouse warehouse, int locationId) throws IllegalArgumentException {
        try {
            Location location = locationService.findLocationById(locationId);
            if (location.getId() > 0) {
                warehouse.setLocation(location);
            }
            return warehouseRepository.save(warehouse);
        } catch (Exception e) {
            throw new IllegalArgumentException("Location does not exist");
        }
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

    public Warehouse saveWarehouse(Warehouse warehouse) {
        warehouseRepository.save(warehouse);
        return warehouse;
    }

    public Warehouse deleteWarehouse(Warehouse warehouse) {
        warehouseRepository.deleteWarehouse(warehouse.getId(), false);
        return warehouse;
    }
}
