package com.skillstorm.inventory_management_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillstorm.inventory_management_backend.models.LotNumber;
import com.skillstorm.inventory_management_backend.models.Warehouse;
import com.skillstorm.inventory_management_backend.models.WarehouseLot;
import com.skillstorm.inventory_management_backend.repositories.WarehouseLotsRepository;
import com.skillstorm.inventory_management_backend.validators.WarehouseLotValidator;

@Service
public class WarehouseLotsService {

    private final WarehouseLotsRepository warehouseLotRepository;
    private final WarehouseService warehouseService;
    private final LotNumberService lotNumberService;

    public WarehouseLotsService(WarehouseLotsRepository warehouseLotRepository, WarehouseService warehouseService,
            LotNumberService lotNumberService) {
        this.warehouseLotRepository = warehouseLotRepository;
        this.warehouseService = warehouseService;
        this.lotNumberService = lotNumberService;
    }

    public List<WarehouseLot> findAllWarehouseLots() {
        return warehouseLotRepository.findAll();
    }

    public WarehouseLot findWarehouseLotsById(int id) {
        Optional<WarehouseLot> warehouseLot = warehouseLotRepository.findById(id);

        if (warehouseLot.isPresent()) {
            return warehouseLot.get();
        }
        throw new IllegalArgumentException("Warehouse lot does not exist. Please try with another warehouse lot.");
    }

    public WarehouseLot createWarehouseLot(WarehouseLot warehouseLot, int warehouseId, int lotNumberId) {
        Warehouse warehouse = warehouseService.findWarehouseById(warehouseId);
        LotNumber lotNumber = lotNumberService.findLotNumberById(lotNumberId);
        warehouseLot.setWarehouse(warehouse);
        warehouseLot.setLotNumber(lotNumber);
        if (WarehouseLotValidator.validateItem(warehouseLot)) {
            return warehouseLotRepository.save(warehouseLot);
        }
        throw new IllegalArgumentException("The warehouse lot was not able to be created.");
    }

    public WarehouseLot saveWarehouseLots(WarehouseLot warehouseLot, int warehouseId, int lotNumberId) {
        if (warehouseId != 0) {
            Warehouse warehouse = warehouseService.findWarehouseById(warehouseId);
            warehouseLot.setWarehouse(warehouse);
        }
        if (lotNumberId != 0) {
            LotNumber lotNumber = lotNumberService.findLotNumberById(lotNumberId);
            warehouseLot.setLotNumber(lotNumber);
        }
        findWarehouseLotsById(warehouseLot.getId());
        if (WarehouseLotValidator.validateItem(warehouseLot)) {
            return warehouseLotRepository.save(warehouseLot);
        }
        throw new IllegalArgumentException("The warehouse lot was not able to be created.");
    }

    public WarehouseLot deleteWarehouseLot(int id) {
        WarehouseLot warehouseLotToBeDeleted = findWarehouseLotsById(id);
        if (warehouseLotToBeDeleted.getId() <= 0) {
            throw new IllegalArgumentException("Warehouse Lot does not exist. Please try with another warehouse lot.");
        }
        warehouseLotRepository.deleteWarehouseLot(id, false);
        return warehouseLotToBeDeleted;
    }
}
