package com.skillstorm.inventory_management_backend;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.skillstorm.inventory_management_backend.models.ItemDetail;
import com.skillstorm.inventory_management_backend.models.LotNumber;
import com.skillstorm.inventory_management_backend.models.StorageBin;
import com.skillstorm.inventory_management_backend.validators.ItemValidator;

public class ItemTest {

    /**
     * An item must be linked to a storage bin.
     * An item must be linked to a lot number.
     * An item must be linked to an item detail.
     */

    @Test
    @DisplayName("StorageBin is not empty")
    public void testStorageBinIsNotEmpty() {
        assertThrows(NullPointerException.class, () -> {
            ItemValidator.storageBinIsNotEmpty(null);
        });
        StorageBin storageBin = new StorageBin();
        assertFalse(ItemValidator.storageBinIsNotEmpty(storageBin));
        storageBin.setId(5);
        assertTrue(ItemValidator.storageBinIsNotEmpty(storageBin));
    }

    @Test
    @DisplayName("LotNumber is not empty")
    public void testLotNumberIsNotEmpty() {
        assertThrows(NullPointerException.class, () -> {
            ItemValidator.lotNumberIsNotEmpty(null);
        });
        LotNumber lotNumber = new LotNumber();
        assertFalse(ItemValidator.lotNumberIsNotEmpty(lotNumber));
        lotNumber.setId(5);
        assertTrue(ItemValidator.lotNumberIsNotEmpty(lotNumber));
    }

    @Test
    @DisplayName("ItemDetail is not empty")
    public void testItemDetailIsNotEmpty() {
        assertThrows(NullPointerException.class, () -> {
            ItemValidator.itemDetailIsNotEmpty(null);
        });
        ItemDetail itemDetail = new ItemDetail();
        assertFalse(ItemValidator.itemDetailIsNotEmpty(itemDetail));
        itemDetail.setId(5);
        assertTrue(ItemValidator.itemDetailIsNotEmpty(itemDetail));
    }
}
