package com.skillstorm.inventory_management_backend.models;

import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "lot")
public class LotNumber {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "date_added", updatable = false)
    @CreationTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime dateAdded;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @OneToMany(mappedBy = "LotNumber")
    @JsonIgnore
    private Set<LotNumber> lotNumber;

    public LotNumber() {
    }

    public LotNumber(LocalDateTime dateAdded, Boolean isActive, int quantity, Item item, Set<LotNumber> lotNumber) {
        this.dateAdded = dateAdded;
        this.isActive = isActive;
        this.quantity = quantity;
        this.item = item;
        this.lotNumber = lotNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Set<LotNumber> getLotNumber() {
        return lotNumber;
    }

    public void setLotNumber(Set<LotNumber> lotNumber) {
        this.lotNumber = lotNumber;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((dateAdded == null) ? 0 : dateAdded.hashCode());
        result = prime * result + ((isActive == null) ? 0 : isActive.hashCode());
        result = prime * result + quantity;
        result = prime * result + ((item == null) ? 0 : item.hashCode());
        result = prime * result + ((lotNumber == null) ? 0 : lotNumber.hashCode());
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
        LotNumber other = (LotNumber) obj;
        if (id != other.id)
            return false;
        if (dateAdded == null) {
            if (other.dateAdded != null)
                return false;
        } else if (!dateAdded.equals(other.dateAdded))
            return false;
        if (isActive == null) {
            if (other.isActive != null)
                return false;
        } else if (!isActive.equals(other.isActive))
            return false;
        if (quantity != other.quantity)
            return false;
        if (item == null) {
            if (other.item != null)
                return false;
        } else if (!item.equals(other.item))
            return false;
        if (lotNumber == null) {
            if (other.lotNumber != null)
                return false;
        } else if (!lotNumber.equals(other.lotNumber))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "LotNumber [id=" + id + ", dateAdded=" + dateAdded + ", isActive=" + isActive + ", quantity=" + quantity
                + ", item=" + item + "]";
    }
}
