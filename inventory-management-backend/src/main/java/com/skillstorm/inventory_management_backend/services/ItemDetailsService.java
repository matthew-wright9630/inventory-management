package com.skillstorm.inventory_management_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.skillstorm.inventory_management_backend.models.ItemDetails;
import com.skillstorm.inventory_management_backend.repositories.ItemDetailsRepository;

@Service
public class ItemDetailsService {

    private final ItemDetailsRepository itemDetailsRepository;

    public ItemDetailsService(ItemDetailsRepository itemDetailsRepository) {
        this.itemDetailsRepository = itemDetailsRepository;
    }

    public List<ItemDetails> findAllItemDetailss() {
        return itemDetailsRepository.findAll();
    }

    public ItemDetails findItemDetailsById(int id) {
        Optional<ItemDetails> ItemDetailsBin = itemDetailsRepository.findById(id);

        if (ItemDetailsBin.isPresent()) {
            return ItemDetailsBin.get();
        }
        return null;
    }

    public ItemDetails createItemDetails(ItemDetails itemDetails) {

        return itemDetailsRepository.save(itemDetails);
    }

    public ItemDetails saveItemDetails(ItemDetails itemDetails) {
        itemDetailsRepository.save(itemDetails);
        return itemDetails;
    }

    public ItemDetails deleteItemDetails(ItemDetails itemDetails) {
        itemDetailsRepository.deleteItemDetails(itemDetails.getId(), false);
        return itemDetails;
    }
}
