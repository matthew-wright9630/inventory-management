package com.skillstorm.inventory_management_backend.validators;

import com.skillstorm.inventory_management_backend.models.Item;
import com.skillstorm.inventory_management_backend.models.ItemDetail;
import com.skillstorm.inventory_management_backend.models.LotNumber;
import com.skillstorm.inventory_management_backend.models.StorageBin;

public class ItemValidator {

    public static boolean validateItem(Item item) {
        return (storageBinIsNotEmpty(item.getStorageBin())
                && itemDetailIsNotEmpty(item.getItemDetail()) && storageBinIsActive(item.getStorageBin())
                && itemDetailIsActive(item.getItemDetail()));
    }

    public static boolean storageBinIsNotEmpty(StorageBin storageBin) {
        try {
            int storageBinId = storageBin.getId();
            if (storageBinId > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public static boolean lotNumberIsNotEmpty(LotNumber lotNumber) {
        try {
            int lotNumberId = lotNumber.getId();
            if (lotNumberId > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public static boolean itemDetailIsNotEmpty(ItemDetail itemDetail) {
        try {
            int itemDetailId = itemDetail.getId();
            if (itemDetailId > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public static boolean storageBinIsActive(StorageBin storageBin) {
        if (storageBin.getIsActive()) {
            return true;
        }
        throw new IllegalArgumentException(
                "Cannot place storage bin in an inactive warehouse. Please select an active warehouse.");
    }

    public static boolean itemDetailIsActive(ItemDetail itemDetail) {
        if (itemDetail.getIsActive()) {
            return true;
        }
        throw new IllegalArgumentException(
                "Cannot place storage bin in an inactive warehouse. Please select an active warehouse.");
    }
}
