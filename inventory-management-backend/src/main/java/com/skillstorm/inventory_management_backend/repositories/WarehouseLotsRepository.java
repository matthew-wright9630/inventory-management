package com.skillstorm.inventory_management_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skillstorm.inventory_management_backend.models.WarehouseLot;

import jakarta.transaction.Transactional;

public interface WarehouseLotsRepository extends JpaRepository<WarehouseLot, Integer> {

    @Query("update WarehouseLot wLot set wLot.isActive = :new_isActive where id = :warehouse_lot_id")
    @Modifying
    @Transactional
    public int deleteWarehouseLot(@Param("warehouse_lot_id") int id, @Param("new_isActive") boolean active);
}
