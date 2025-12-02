package com.skillstorm.inventory_management_backend;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.skillstorm.inventory_management_backend.models.Warehouse;

public class WarehouseTest {

    /*
        For a warehouse to be created:
            Name needs to be added
                - Name must contain at least 3 characters
                - Name cannot be empty
            Location needs to be added
                - Location must include a country
                        - Location country name must contain at least 3 characters
                        - Country cannot be empty
                - Locaiton must include a city
                        - Location city name must contain at least 3 characters
                        - City cannot be empty
                - Location must include an address
                        - Address must contain at least 3 characters
                        - Address cannot be empty
                - Location should include a state/region field
                        - State/Region must contain at least 3 characters
                        - State/Region cannot be empty
            Maximum capacity must be added
                - Max Capacity must be an integer value
                - Max Capacity must be greater than 0
    */

    // Creation Tests
    @Test
    @DisplayName("Name has at least three characters")
    public void testNameHasThreeCharacters() {
        assertFalse(Warehouse.hasThreeCharacters(""));
        assertFalse(Warehouse.hasThreeCharacters("Hi"));
        assertTrue(Warehouse.notEmptyString("Warehouse 1"));
    }

    @Test
    @DisplayName("Name is not null") 
    public void testNameIsNotNull(){
        assertThrows(NullPointerException.class, () -> {
            Warehouse.notEmptyString(null);
        });
        assertTrue(Warehouse.notEmptyString("Warehouse 1"));
    }

    @Test
    @DisplayName("Country name has at least three characters")
    public void testCountryHasThreeCharacters() {
        assertFalse(Warehouse.hasThreeCharacters(""));
        assertFalse(Warehouse.hasThreeCharacters("US"));
        assertThrows(NullPointerException.class, () -> {
            Warehouse.hasThreeCharacters(null);
        });
        assertTrue(Warehouse.hasThreeCharacters("United States of America"));
    }
    
    @Test
    @DisplayName("Country is not null") 
    public void testCountryIsNotNull(){
        assertThrows(NullPointerException.class, () -> {
            Warehouse.notEmptyString(null);
        });
        assertTrue(Warehouse.notEmptyString("United Kingdom"));
    }

    @Test
    @DisplayName("City name has at least three characters")
    public void testCityHasThreeCharacters() {
        assertFalse(Warehouse.hasThreeCharacters(""));
        assertFalse(Warehouse.hasThreeCharacters("aa"));
        assertThrows(NullPointerException.class, () -> {
            Warehouse.hasThreeCharacters(null);
        });
        assertTrue(Warehouse.hasThreeCharacters("Woodburn"));
    }
    
    @Test
    @DisplayName("City is not null") 
    public void testCityIsNotNull() {
        assertThrows(NullPointerException.class, () -> {
            Warehouse.notEmptyString(null);
        });
        assertTrue(Warehouse.notEmptyString("Greenville"));
    }

    @Test
    @DisplayName("Address has at least three characters")
    public void testAddressHasThreeCharacters() {
        assertFalse(Warehouse.hasThreeCharacters(""));
        assertFalse(Warehouse.hasThreeCharacters("aa"));
        assertThrows(NullPointerException.class, () -> {
            Warehouse.hasThreeCharacters(null);
        });
        assertTrue(Warehouse.hasThreeCharacters("987 Testing Ave."));
    }

    @Test
    @DisplayName("Location Address is not Null")
    public void testAddressIsNotNull() {
        assertThrows(NullPointerException.class, () -> {
            Warehouse.notEmptyString(null);
        });
        assertTrue(Warehouse.notEmptyString("1234 Test Rd"));
    }

    @Test
    @DisplayName("State or region has at least two characters")
    public void testStateOrRegionHasThreeCharacters() {
        assertFalse(Warehouse.hasTwoCharacters(""));
        assertFalse(Warehouse.hasTwoCharacters("a"));
        assertThrows(NullPointerException.class, () -> {
            Warehouse.hasTwoCharacters(null);
        });
        assertTrue(Warehouse.hasTwoCharacters("IN"));
        assertTrue(Warehouse.hasTwoCharacters("Idiana"));
    }

    @Test
    @DisplayName("State or region is not Null")
    public void testStateOrRegionIsNotNull() {
        assertThrows(NullPointerException.class, () -> {
            Warehouse.notEmptyString(null);
        });
        assertTrue(Warehouse.notEmptyString("South Carolina"));
    }

    @Test
    @DisplayName("Max Capacity is an integer")
    public void testMaxCapacityIsInteger() {
        assertThrows(NumberFormatException.class, () -> {
            Warehouse.inputIsInteger("Test");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Warehouse.inputIsInteger("15.5");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Warehouse.inputIsInteger("true");
        });
        assertTrue(Warehouse.inputIsInteger("10000"));
    }

    @Test
    @DisplayName("Maximum Capacity is greater than zero")
    public void testMaxCapacityIsGreaterThanZero() {
        assertFalse(Warehouse.greaterThanZero(0));
        assertFalse(Warehouse.greaterThanZero(-100));
        assertTrue(Warehouse.greaterThanZero(1000));
    }
}
