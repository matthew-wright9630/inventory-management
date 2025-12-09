package com.skillstorm.inventory_management_backend.validators;

import java.util.List;

import com.skillstorm.inventory_management_backend.models.StorageBin;
import com.skillstorm.inventory_management_backend.models.Warehouse;

public class StorageBinValidator {

    public static boolean validateStorageBin(StorageBin storageBin, List<StorageBin> activeStorageBins) {
        return (notEmptyString(storageBin.getStorageLocation())
                && warehouseIsNotEmpty(storageBin.getWarehouse())
                && storageBinLocationIsUnique(activeStorageBins, storageBin));
    }

    public static boolean notEmptyString(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input string cannot be null.");
        }
        return true;
    }

    public static boolean warehouseIsNotEmpty(Warehouse warehouse) {
        try {
            int warehouseId = warehouse.getId();
            if (warehouseId > 0) {
                return true;
            } else {
                return false;
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Warehouse is required to add to a storage bin.");
        } catch (Exception e) {
            throw e;
        }
    }

    public static boolean storageBinLocationIsUnique(List<StorageBin> activeStorageBins, StorageBin newStorageBin) {
        try {
            for (StorageBin bin : activeStorageBins) {
                if (bin.getStorageLocation().equals(newStorageBin.getStorageLocation())) {
                    throw new IllegalArgumentException(
                            "There is already a storage bin at this location. Please select a different storage location");
                }
            }
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    public static boolean warehouseIsActive(Warehouse warehouse) {
        if (warehouse.isActive()) {
            return true;
        }
        throw new IllegalArgumentException(
                "Cannot place storage bin in an inactive warehouse. Please select an active warehouse.");
    }
}
