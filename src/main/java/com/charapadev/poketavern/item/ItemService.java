package com.charapadev.poketavern.item;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> list() {
        return itemRepository.findAll();
    }

    public Item create(CreateItemDTO creationDTO) {
        Item newItem = new Item(creationDTO.name(), creationDTO.description());
        
        return itemRepository.save(newItem);
    }

    public Item find(Long id) throws NoSuchElementException {
        return itemRepository.findById(id)
            .orElseThrow();
    }

}
