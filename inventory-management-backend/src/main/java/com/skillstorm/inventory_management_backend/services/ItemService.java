package com.skillstorm.inventory_management_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RestController;

import com.skillstorm.inventory_management_backend.models.Item;
import com.skillstorm.inventory_management_backend.repositories.ItemRepository;
import com.skillstorm.inventory_management_backend.validators.ItemValidator;

@RestController
public class ItemService {

    private final ItemRepository itemsRepository;

    public ItemService(ItemRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    public List<Item> findAllItems() {
        return itemsRepository.findAll();
    }

    public Item findItemById(int id) {
        Optional<Item> ItemsBin = itemsRepository.findById(id);

        if (ItemsBin.isPresent()) {
            return ItemsBin.get();
        }
        return null;
    }

    public Item createItem(Item items) {
        ItemValidator.validateItem(items);
        return itemsRepository.save(items);
    }

    public Item saveItem(Item items) {
        itemsRepository.save(items);
        return items;
    }

    public Item deleteItem(Item item) {
        itemsRepository.deleteItem(item.getId(), false);
        return item;
    }
}
