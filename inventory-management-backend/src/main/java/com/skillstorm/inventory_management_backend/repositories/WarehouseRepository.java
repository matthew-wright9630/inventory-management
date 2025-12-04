package com.skillstorm.inventory_management_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skillstorm.inventory_management_backend.models.Warehouse;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {

}
