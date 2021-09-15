package com.shopping.cartmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Entity(name="items")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    @NotBlank(message = "Item name cant be empty")
    private String name;

    @Column(length = 50, nullable = false)
    @NotBlank(message = "Category cant be empty")
    private String category;

    @Column
    @Positive(message = "Quantity should be more than zero")
    private int quantity;

    @Column
    @Positive(message = "Price should be more than zero")
    private long price;

    @Column(length = 50, nullable = false)
    @NotBlank(message = "Brand name cant be empty")
    private String brandName;

}
