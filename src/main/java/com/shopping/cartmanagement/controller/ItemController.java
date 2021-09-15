package com.shopping.cartmanagement.controller;

import com.shopping.cartmanagement.entity.Item;
import com.shopping.cartmanagement.exception.ItemNotFoundException;
import com.shopping.cartmanagement.service.ItemService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    @ApiOperation(value = "Add Items")
    public ResponseEntity<Item> add(@Valid @RequestBody Item item){
        return new ResponseEntity<>(itemService.add(item), HttpStatus.OK);
    }

    @PutMapping
    @ApiOperation(value = "Update Item")
    public ResponseEntity<Item> update(@Valid @RequestBody Item item) throws ItemNotFoundException {
        return new ResponseEntity<>(itemService.update(item), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Fetch Item by ID")
    public ResponseEntity<Item> fetch(@PathVariable Long id) throws ItemNotFoundException {
        return new ResponseEntity<>(itemService.fetch(id),HttpStatus.OK);
    }

}
