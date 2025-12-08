package com.skillstorm.inventory_management_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillstorm.inventory_management_backend.models.WarehouseLots;
import com.skillstorm.inventory_management_backend.repositories.WarehouseLotsRepository;

@Service
public class WarehouseLotsService {

    private final WarehouseLotsRepository warehouseLotsRepository;

    public WarehouseLotsService(WarehouseLotsRepository warehouseLotsRepository) {
        this.warehouseLotsRepository = warehouseLotsRepository;
    }

    public List<WarehouseLots> findAllWarehouseLotss() {
        return warehouseLotsRepository.findAll();
    }

    public WarehouseLots findWarehouseLotsById(int id) {
        Optional<WarehouseLots> warehouseLots = warehouseLotsRepository.findById(id);

        if (warehouseLots.isPresent()) {
            return warehouseLots.get();
        }
        return null;
    }

    public WarehouseLots saveWarehouseLots(WarehouseLots warehouseLots) {
        warehouseLotsRepository.save(warehouseLots);
        return warehouseLots;
    }

    public WarehouseLots deleteWarehouseLots(WarehouseLots warehouseLots) {
        warehouseLotsRepository.deleteWarehouseLots(warehouseLots.getId(), false);
        return warehouseLots;
    }
}
