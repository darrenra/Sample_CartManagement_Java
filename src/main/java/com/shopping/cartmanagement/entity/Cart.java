package com.shopping.cartmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "cart")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    @OneToMany(targetEntity = Item.class,fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Item> items;

    private double total;


    public void addItem(Item item){
        if(items==null){
            this.items=new ArrayList<>();
        }
        this.items.add(item);
    }

}
