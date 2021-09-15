package com.shopping.cartmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartActionModel {
    private Long itemId;
    private String userId;
}
