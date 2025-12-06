package com.skillstorm.inventory_management_backend.validators;

import java.util.List;

import com.skillstorm.inventory_management_backend.models.StorageBin;
import com.skillstorm.inventory_management_backend.models.Warehouse;

public class StorageBinValidator {

    public static boolean validateStorageBin(StorageBin storageBin, List<String> activeLocations) {
        return (notEmptyString(""));
    }

    public static boolean notEmptyString(String input) {
        try {
            return !input.isEmpty();
        } catch (Exception e) {
            throw e;
        }
    }

    public static boolean hasTwoCharacters(String input) {
        return input.length() >= 2;
    }

    public static boolean warehouseIsNotEmpty(Warehouse warehouse) {
        try {
            return !warehouse.isEmpty();

        } catch (Exception e) {
            throw e;
        }
    }

    public static boolean storageBinLocationIsUnique(String[] storageLocationArray, StorageBin storageBin2) {
        String newStorageLocation = storageBin2.getStorageLocation();
        try {
            for (String s : storageLocationArray) {
                if (s.equals(newStorageLocation)) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            throw e;
        }
    }
}
