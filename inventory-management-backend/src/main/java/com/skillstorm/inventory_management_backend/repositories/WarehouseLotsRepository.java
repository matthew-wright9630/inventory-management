package com.skillstorm.inventory_management_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skillstorm.inventory_management_backend.models.WarehouseLots;

import jakarta.transaction.Transactional;

public interface WarehouseLotsRepository extends JpaRepository<WarehouseLots, Integer> {

    @Query("update WarehouseLots wl set wl.isActive = :new_isActive where id = :warehouse_lots_id")
    @Modifying
    @Transactional
    public int deleteWarehouseLots(@Param("warehouse_lots_id") int id, @Param("new_isActive") boolean active);
}
