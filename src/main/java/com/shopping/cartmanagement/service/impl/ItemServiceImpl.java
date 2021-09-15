package com.shopping.cartmanagement.service.impl;

import com.shopping.cartmanagement.entity.Item;
import com.shopping.cartmanagement.exception.ItemNotFoundException;
import com.shopping.cartmanagement.repository.ItemRepository;
import com.shopping.cartmanagement.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item add(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public boolean available(Long id) {
        Optional<Item> item = itemRepository.findById(id);
        return item.isPresent() && item.get().getPrice() > 0;
    }

    @Override
    public Item update(Item item) throws ItemNotFoundException {
        Optional<Item> existing = itemRepository.findById(item.getId());
        existing.orElseThrow(() -> new ItemNotFoundException("Item not available"));
        return itemRepository.save(item);
    }

    @Override
    public Item fetch(Long itemId) throws ItemNotFoundException {
        return itemRepository.findById(itemId).orElseThrow(()->new ItemNotFoundException("Item not available"));
    }
}
