package com.skillstorm.inventory_management_backend;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.skillstorm.inventory_management_backend.models.ItemDetail;
import com.skillstorm.inventory_management_backend.models.StorageBin;
import com.skillstorm.inventory_management_backend.validators.ItemValidator;

public class ItemTest {

    /**
     * An item must be linked to a storage bin.
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
    @DisplayName("StorageBin is active")
    public void testStorageBinIsactive() {
        StorageBin storageBin = new StorageBin();
        storageBin.setIsActive(false);
        assertThrows(IllegalArgumentException.class, () -> {
            ItemValidator.storageBinIsActive(storageBin);
        });
        storageBin.setIsActive(true);
        assertTrue(ItemValidator.storageBinIsActive(storageBin));
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

    @Test
    @DisplayName("ItemDetail is active")
    public void testItemDetailIsactive() {
        ItemDetail itemDetail = new ItemDetail();
        itemDetail.setIsActive(false);
        assertThrows(IllegalArgumentException.class, () -> {
            ItemValidator.itemDetailIsActive(itemDetail);
        });
        itemDetail.setIsActive(true);
        assertTrue(ItemValidator.itemDetailIsActive(itemDetail));
    }

}
