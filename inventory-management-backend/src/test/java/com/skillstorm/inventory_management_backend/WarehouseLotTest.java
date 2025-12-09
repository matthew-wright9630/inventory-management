package com.skillstorm.inventory_management_backend;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.skillstorm.inventory_management_backend.models.LotNumber;
import com.skillstorm.inventory_management_backend.models.Warehouse;
import com.skillstorm.inventory_management_backend.validators.WarehouseLotValidator;

public class WarehouseLotTest {

    /**
     * A warehouse lot must be linked to a lot number.
     * A warehouse lot must be linked to a warehouse.
     */

    @Test
    @DisplayName("Warehouse is not empty")
    public void testWarehouseIsNotEmpty() {
        assertThrows(NullPointerException.class, () -> {
            WarehouseLotValidator.warehouseIsNotEmpty(null);
        });
        Warehouse warehouse = new Warehouse();
        assertFalse(WarehouseLotValidator.warehouseIsNotEmpty(warehouse));
        warehouse.setId(5);
        assertTrue(WarehouseLotValidator.warehouseIsNotEmpty(warehouse));
    }

    @Test
    @DisplayName("Warehouse is active")
    public void testWarehouseIsactive() {
        Warehouse warehouse = new Warehouse();
        warehouse.setActive(false);
        assertThrows(IllegalArgumentException.class, () -> {
            WarehouseLotValidator.warehouseIsActive(warehouse);
        });
        warehouse.setActive(true);
        assertTrue(WarehouseLotValidator.warehouseIsActive(warehouse));
    }

    @Test
    @DisplayName("LotNumber is not empty")
    public void testLotNumberIsNotEmpty() {
        assertThrows(NullPointerException.class, () -> {
            WarehouseLotValidator.lotNumberIsNotEmpty(null);
        });
        LotNumber lotNumber = new LotNumber();
        assertFalse(WarehouseLotValidator.lotNumberIsNotEmpty(lotNumber));
        lotNumber.setId(5);
        assertTrue(WarehouseLotValidator.lotNumberIsNotEmpty(lotNumber));
    }

    @Test
    @DisplayName("LotNumber is active")
    public void testLotNumberIsactive() {
        LotNumber lotNumber = new LotNumber();
        lotNumber.setIsActive(false);
        assertThrows(IllegalArgumentException.class, () -> {
            WarehouseLotValidator.lotNumberIsActive(lotNumber);
        });
        lotNumber.setIsActive(true);
        assertTrue(WarehouseLotValidator.lotNumberIsActive(lotNumber));
    }
}
