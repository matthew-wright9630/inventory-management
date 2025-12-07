package com.skillstorm.inventory_management_backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.skillstorm.inventory_management_backend.models.StorageBin;
import com.skillstorm.inventory_management_backend.models.Warehouse;

import jakarta.transaction.Transactional;

@Repository
public interface StorageBinRepository extends JpaRepository<StorageBin, Integer> {

    List<StorageBin> findByWarehouseIdAndIsActive(Warehouse warehouse, Boolean isActive);

    @Query("update StorageBin bin set bin.isActive = :new_isActive where id = :storage_bin_id")
    @Modifying
    @Transactional
    public int deleteStorageBin(@Param("storage_bin_id") int id, @Param("new_isActive") boolean active);
}