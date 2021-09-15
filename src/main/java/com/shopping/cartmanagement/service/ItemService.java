package com.shopping.cartmanagement.service;

import com.shopping.cartmanagement.entity.Item;
import com.shopping.cartmanagement.exception.ItemNotFoundException;

public interface ItemService {

    Item add(Item item);

    boolean available(Long id);

    Item update(Item item) throws ItemNotFoundException;

    Item fetch(Long itemId) throws ItemNotFoundException;

}
