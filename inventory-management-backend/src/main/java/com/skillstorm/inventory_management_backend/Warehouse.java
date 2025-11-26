package com.skillstorm.inventory_management_backend;

public class Warehouse {
    
    public static boolean hasThreeCharacters(String input) {
            return input.length() >= 3;
    }

    public static boolean notEmptyString(String input) {
        try {
            return !input.isEmpty();
        } catch (Exception e) {
            throw e;
        }
    }
}
