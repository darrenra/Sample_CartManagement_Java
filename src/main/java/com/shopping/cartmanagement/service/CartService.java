package com.shopping.cartmanagement.service;

import com.shopping.cartmanagement.entity.Cart;
import com.shopping.cartmanagement.exception.CartNotFoundException;
import com.shopping.cartmanagement.exception.ItemNotFoundException;

import java.util.Optional;

public interface CartService {

    Cart add(Long itemId, String userId) throws ItemNotFoundException;

    Cart remove(Long itemId,String userId) throws CartNotFoundException;

    Optional<Cart> get(String userId);
}
