package com.shopping.cartmanagement.service.impl;

import com.shopping.cartmanagement.entity.Cart;
import com.shopping.cartmanagement.entity.Item;
import com.shopping.cartmanagement.exception.CartNotFoundException;
import com.shopping.cartmanagement.exception.ItemNotFoundException;
import com.shopping.cartmanagement.repository.CartRepository;
import com.shopping.cartmanagement.service.CartService;
import com.shopping.cartmanagement.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository repository;

    @Autowired
    private ItemService itemService;

    @Override
    public Cart add(Long itemId, String userId) throws ItemNotFoundException {
        Cart cart=get(userId).orElseGet(Cart::new);
        cart.setUserId(userId);
        Item item = itemService.fetch(itemId);
        cart.addItem(item);
        cart.setTotal(cart.getItems().stream().mapToDouble(Item::getPrice).sum());
        return repository.save(cart);
    }

    @Override
    public Cart remove(Long itemId, String userId) throws CartNotFoundException {
        Cart cart=get(userId).orElseThrow(()->new CartNotFoundException("Cart is not available"));
        if(cart.getItems()!=null && !cart.getItems().isEmpty()){
            cart.setItems(cart.getItems().stream().filter(item -> !item.getId().equals(itemId)).collect(Collectors.toList()));
            cart.setTotal(cart.getItems().stream().mapToDouble(Item::getPrice).sum());
        }
        return repository.save(cart);
    }

    @Override
    public Optional<Cart> get(String userId) {
        return repository.findByUserId(userId);
    }

}
