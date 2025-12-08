package com.skillstorm.inventory_management_backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class WarehouseLots {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "lotNumber_id")
    private LotNumber lotNumber;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    public WarehouseLots() {
    }

    public WarehouseLots(LotNumber lotNumber, Warehouse warehouse) {
        this.lotNumber = lotNumber;
        this.warehouse = warehouse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LotNumber getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(LotNumber lotNumber) {
        this.lotNumber = lotNumber;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((lotNumber == null) ? 0 : lotNumber.hashCode());
        result = prime * result + ((warehouse == null) ? 0 : warehouse.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        WarehouseLots other = (WarehouseLots) obj;
        if (id != other.id)
            return false;
        if (lotNumber == null) {
            if (other.lotNumber != null)
                return false;
        } else if (!lotNumber.equals(other.lotNumber))
            return false;
        if (warehouse == null) {
            if (other.warehouse != null)
                return false;
        } else if (!warehouse.equals(other.warehouse))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "WarehouseLots [id=" + id + "]";
    }
}
