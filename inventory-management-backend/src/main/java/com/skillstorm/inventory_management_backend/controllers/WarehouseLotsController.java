package com.skillstorm.inventory_management_backend.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.inventory_management_backend.models.WarehouseLots;
import com.skillstorm.inventory_management_backend.services.WarehouseLotsService;

@RestController
@RequestMapping("/warehouse-lots")
public class WarehouseLotsController {

    private final WarehouseLotsService warehouseLotsService;

    public WarehouseLotsController(WarehouseLotsService warehouseLotsService) {
        this.warehouseLotsService = warehouseLotsService;
    }

    @GetMapping
    public ResponseEntity<List<WarehouseLots>> findAllWarehouseLotss() {
        try {
            List<WarehouseLots> warehouseLotss = warehouseLotsService.findAllWarehouseLotss();
            return new ResponseEntity<>(warehouseLotss, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @PostMapping
    public ResponseEntity<WarehouseLots> createWarehouseLots(@RequestBody WarehouseLots warehouseLots) {
        try {
            return new ResponseEntity<>(warehouseLotsService.saveWarehouseLots(warehouseLots), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @PutMapping
    public ResponseEntity<WarehouseLots> updateWarehouseLots(@RequestBody WarehouseLots warehouseLots) {
        try {
            WarehouseLots newWarehouseLots = warehouseLotsService.saveWarehouseLots(warehouseLots);
            return new ResponseEntity<WarehouseLots>(newWarehouseLots, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarehouseLots(@PathVariable int id) {
        try {
            WarehouseLots warehouseLots = warehouseLotsService.findWarehouseLotsById(id);
            warehouseLotsService.deleteWarehouseLots(warehouseLots);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }
}
