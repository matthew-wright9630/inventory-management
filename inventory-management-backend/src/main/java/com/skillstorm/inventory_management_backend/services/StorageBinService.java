package com.skillstorm.inventory_management_backend.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.skillstorm.inventory_management_backend.models.StorageBin;
import com.skillstorm.inventory_management_backend.models.Warehouse;
import com.skillstorm.inventory_management_backend.repositories.StorageBinRepository;

public class StorageBinService {
    private final StorageBinRepository storageBinRepository;

    public StorageBinService(StorageBinRepository storageBinRepository) {
        this.storageBinRepository = storageBinRepository;
    }

    public List<StorageBin> findAllStorageBins() {
        return storageBinRepository.findAll();
    }

    public StorageBin findStorageBinById(int id) {
        Optional<StorageBin> storageBin = storageBinRepository.findById(id);

        if (storageBin.isPresent()) {
            return storageBin.get();
        }
        return null;
    }

    public List<String> findAllActiveStorageBinLocationsInWarehouse(Warehouse warehouse) {
        List<StorageBin> activeStorageBins = storageBinRepository.findByWarehouseIdAndIsActive(warehouse, true);
        List<String> listOfActiveLocations = new ArrayList<>();
        for (StorageBin bin : activeStorageBins) {
            listOfActiveLocations.add(bin.getStorageLocation());
        }
        return listOfActiveLocations;
    }

    public StorageBin createStorageBin(StorageBin storageBin) {
        return storageBinRepository.save(storageBin);
    }

    public StorageBin saveStorageBin(StorageBin storageBin) {
        storageBinRepository.save(storageBin);
        return storageBin;
    }

    public StorageBin deleteStorageBin(StorageBin storageBin) {
        storageBinRepository.deleteStorageBin(storageBin.getId(), false);
        return storageBin;
    }
}
