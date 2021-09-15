package com.shopping.cartmanagement.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopping.cartmanagement.entity.Cart;
import com.shopping.cartmanagement.entity.Item;
import com.shopping.cartmanagement.model.CartActionModel;
import com.shopping.cartmanagement.service.CartService;
import com.shopping.cartmanagement.service.ItemService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    @MockBean
    private ItemService itemService;

    @Test
    void testAddDataSuccess() throws Exception {
        Mockito.when(cartService.add(1L, "abc")).thenReturn(mockCart());
        this.mockMvc.perform(post("/api/v1/cart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJsonCartItem()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(new ObjectMapper().writeValueAsString(mockCart())));
    }

    @Test
    void testGetUserData() throws Exception {
        Mockito.when(cartService.get("abc")).thenReturn(Optional.of(mockCart()));
        this.mockMvc.perform(get("/api/v1/cart?userId=abc"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(new ObjectMapper().writeValueAsString(mockCart())));
    }

    private Cart mockCart() {
        Cart cart = new Cart();
        cart.setTotal(1000);
        cart.setUserId("abc");
        cart.setId(1L);
        cart.setItems(Arrays.asList(
                Item.builder()
                        .brandName("Apple")
                        .category("Mobile")
                        .name("iPhone")
                        .price(1000)
                        .quantity(100)
                        .id(1L)
                        .build()));
        return cart;
    }

    private String getJsonCartItem() throws JsonProcessingException {
        CartActionModel model=new CartActionModel();
        model.setItemId(1L);
        model.setUserId("abc");
        return new ObjectMapper().writeValueAsString(model);
    }
}
