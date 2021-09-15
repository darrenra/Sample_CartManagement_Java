package com.shopping.cartmanagement.controller;

import com.shopping.cartmanagement.entity.Cart;
import com.shopping.cartmanagement.exception.CartNotFoundException;
import com.shopping.cartmanagement.exception.ItemNotFoundException;
import com.shopping.cartmanagement.model.CartActionModel;
import com.shopping.cartmanagement.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public ResponseEntity<Cart> fetch(@RequestParam("userId") String userId) throws CartNotFoundException {
        Optional<Cart> optionalCart = cartService.get(userId);
        Cart cart = optionalCart.orElseThrow(() -> new CartNotFoundException("No cart is available for user"));
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cart> add(@RequestBody CartActionModel cartActionModel) throws ItemNotFoundException {
        return new ResponseEntity<>(cartService.add(cartActionModel.getItemId(), cartActionModel.getUserId()),HttpStatus.OK);
    }

    @PostMapping("/remove")
    public ResponseEntity<Cart> remove(@RequestBody CartActionModel cartActionModel) throws CartNotFoundException {
        return new ResponseEntity<>(cartService.remove(cartActionModel.getItemId(), cartActionModel.getUserId()),HttpStatus.OK);
    }

}
