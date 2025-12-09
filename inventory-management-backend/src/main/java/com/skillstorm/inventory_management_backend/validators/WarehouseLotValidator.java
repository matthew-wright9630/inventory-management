package com.skillstorm.inventory_management_backend.validators;

import com.skillstorm.inventory_management_backend.models.Warehouse;
import com.skillstorm.inventory_management_backend.models.WarehouseLot;
import com.skillstorm.inventory_management_backend.models.LotNumber;

public class WarehouseLotValidator {

    public static boolean validateItem(WarehouseLot warehouseLot) {
        return (lotNumberIsNotEmpty(warehouseLot.getLotNumber())
                && warehouseIsNotEmpty(warehouseLot.getWarehouse()) && lotNumberIsActive(warehouseLot.getLotNumber())
                && warehouseIsActive(warehouseLot.getWarehouse()));
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

    public static boolean warehouseIsNotEmpty(Warehouse warehouse) {
        try {
            int warehouseId = warehouse.getId();
            if (warehouseId > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public static boolean lotNumberIsActive(LotNumber lotNumber) {
        if (lotNumber.getIsActive()) {
            return true;
        }
        throw new IllegalArgumentException(
                "Cannot place storage bin in an inactive warehouse. Please select an active warehouse.");
    }

    public static boolean warehouseIsActive(Warehouse warehouse) {
        if (warehouse.isActive()) {
            return true;
        }
        throw new IllegalArgumentException(
                "Cannot place storage bin in an inactive warehouse. Please select an active warehouse.");
    }
}
