package com.skillstorm.inventory_management_backend;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.skillstorm.inventory_management_backend.validators.ItemDetailsValidator;

public class ItemDetailsTest {

    /**
     * Item details must include a name
     * - Name must not be empty
     * - Name must be at least 3 characters long
     * Item details must include a SKU
     * - SKU must not be empty
     * Item details must include a description
     * - Description must not be empty
     * - Description must be at least 3 characters long
     * Item details can include an optional shelf-life
     * - If shelf-life is added, date must be greater than 0 days.
     */

    @Test
    @DisplayName("Item Details Name has at least three characters")
    public void testNameHasThreeCharacters() {
        assertThrows(IllegalArgumentException.class, () -> {
            ItemDetailsValidator.hasThreeCharacters("");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            ItemDetailsValidator.hasThreeCharacters("IT");
        });
        assertTrue(ItemDetailsValidator.notEmptyString("Item details 1"));
    }

    @Test
    @DisplayName("Item Details Name is not null")
    public void testNameIsNotNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            ItemDetailsValidator.notEmptyString(null);
        });
        assertTrue(ItemDetailsValidator.notEmptyString("New item details"));
    }

    @Test
    @DisplayName("SKU number is not null")
    public void testSKUIsNotNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            ItemDetailsValidator.notEmptyString(null);
        });
        assertTrue(ItemDetailsValidator.notEmptyString("1"));
        assertTrue(ItemDetailsValidator.notEmptyString("34562-x5-433"));
    }

    @Test
    @DisplayName("Item description field has at least three characters")
    public void testDescriptionHasThreeCharacters() {
        assertThrows(IllegalArgumentException.class, () -> {
            ItemDetailsValidator.hasThreeCharacters("");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            ItemDetailsValidator.hasThreeCharacters("aa");
        });
        assertTrue(ItemDetailsValidator
                .hasThreeCharacters("This is a details of a test item. A real item does not exist."));
    }

    @Test
    @DisplayName("Item description field is not Null")
    public void testDescriptionIsNotNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            ItemDetailsValidator.notEmptyString(null);
        });
        assertTrue(ItemDetailsValidator.notEmptyString("Test Description"));
    }

    @Test
    @DisplayName("Shelf-life is an integer")
    public void testShelfLifeIsInteger() {
        assertThrows(IllegalArgumentException.class, () -> {
            ItemDetailsValidator.inputIsInteger("Test");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            ItemDetailsValidator.inputIsInteger("15.5");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            ItemDetailsValidator.inputIsInteger("true");
        });
        assertTrue(ItemDetailsValidator.inputIsInteger("10000"));
    }

    @Test
    @DisplayName("Shelf life is greater than zero")
    public void testShelfLifeIsGreaterThanZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            ItemDetailsValidator.greaterThanZero(0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            ItemDetailsValidator.greaterThanZero(-100);
        });
        assertTrue(ItemDetailsValidator.greaterThanZero(1000));
    }
}
