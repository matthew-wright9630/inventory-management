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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.inventory_management_backend.models.WarehouseLot;
import com.skillstorm.inventory_management_backend.services.WarehouseLotsService;

@RestController
@RequestMapping("/warehouse-lots")
public class WarehouseLotsController {

    private final WarehouseLotsService warehouseLotsService;

    public WarehouseLotsController(WarehouseLotsService warehouseLotsService) {
        this.warehouseLotsService = warehouseLotsService;
    }

    @GetMapping
    public ResponseEntity<List<WarehouseLot>> findAllWarehouseLotss() {
        try {
            List<WarehouseLot> warehouseLots = warehouseLotsService.findAllWarehouseLots();
            return new ResponseEntity<>(warehouseLots, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().header("message", e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @PostMapping
    public ResponseEntity<WarehouseLot> createWarehouseLots(@RequestBody WarehouseLot warehouseLots,
            @RequestParam int warehouseId, @RequestParam int lotNumberId) {
        try {
            return new ResponseEntity<>(
                    warehouseLotsService.createWarehouseLot(warehouseLots, warehouseId, lotNumberId),
                    HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().header("message", e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @PutMapping
    public ResponseEntity<WarehouseLot> updateWarehouseLots(@RequestBody WarehouseLot warehouseLots,
            @RequestParam(defaultValue = "0") int warehouseId, @RequestParam(defaultValue = "0") int lotNumberId) {
        try {
            WarehouseLot newWarehouseLot = warehouseLotsService.saveWarehouseLots(warehouseLots, warehouseId,
                    lotNumberId);
            return new ResponseEntity<WarehouseLot>(newWarehouseLot, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().header("message", e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarehouseLot(@PathVariable int id) {
        try {
            warehouseLotsService.deleteWarehouseLot(id);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().header("message", e.getMessage()).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("message", e.getMessage()).build();
        }
    }
}
