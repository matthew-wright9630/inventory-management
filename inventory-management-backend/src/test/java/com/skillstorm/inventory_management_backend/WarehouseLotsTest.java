package com.skillstorm.inventory_management_backend;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.skillstorm.inventory_management_backend.models.LotNumber;
import com.skillstorm.inventory_management_backend.models.Warehouse;
import com.skillstorm.inventory_management_backend.validators.WarehouseLotsValidator;

public class WarehouseLotsTest {

    /**
     * A warehouse lot must be linked to a lot number.
     * A warehouse lot must be linked to a warehouse.
     */

    @Test
    @DisplayName("Warehouse is not empty")
    public void testWarehouseIsNotEmpty() {
        assertThrows(NullPointerException.class, () -> {
            WarehouseLotsValidator.warehouseIsNotEmpty(null);
        });
        Warehouse warehouse = new Warehouse();
        assertFalse(WarehouseLotsValidator.warehouseIsNotEmpty(warehouse));
        warehouse.setId(5);
        assertTrue(WarehouseLotsValidator.warehouseIsNotEmpty(warehouse));
    }

    @Test
    @DisplayName("Warehouse is active")
    public void testWarehouseIsactive() {
        Warehouse warehouse = new Warehouse();
        warehouse.setIsActive(false);
        assertThrows(IllegalArgumentException.class, () -> {
            WarehouseLotsValidator.warehouseIsActive(warehouse);
        });
        warehouse.setIsActive(true);
        assertTrue(WarehouseLotsValidator.warehouseIsActive(warehouse));
    }

    @Test
    @DisplayName("LotNumber is not empty")
    public void testLotNumberIsNotEmpty() {
        assertThrows(NullPointerException.class, () -> {
            WarehouseLotsValidator.lotNumberIsNotEmpty(null);
        });
        LotNumber lotNumber = new LotNumber();
        assertFalse(WarehouseLotsValidator.lotNumberIsNotEmpty(lotNumber));
        lotNumber.setId(5);
        assertTrue(WarehouseLotsValidator.lotNumberIsNotEmpty(lotNumber));
    }

    @Test
    @DisplayName("LotNumber is active")
    public void testLotNumberIsactive() {
        LotNumber lotNumber = new LotNumber();
        lotNumber.setIsActive(false);
        assertThrows(IllegalArgumentException.class, () -> {
            WarehouseLotsValidator.lotNumberIsActive(lotNumber);
        });
        lotNumber.setIsActive(true);
        assertTrue(WarehouseLotsValidator.lotNumberIsActive(lotNumber));
    }
}
