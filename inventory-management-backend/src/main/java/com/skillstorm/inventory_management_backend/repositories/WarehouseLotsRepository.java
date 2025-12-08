package com.skillstorm.inventory_management_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillstorm.inventory_management_backend.models.WarehouseLots;

public interface WarehouseLotsRepository extends JpaRepository<WarehouseLots, Integer> {
}
