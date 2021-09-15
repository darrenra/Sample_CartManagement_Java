package com.shopping.cartmanagement.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopping.cartmanagement.entity.Item;
import com.shopping.cartmanagement.service.ItemService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    @Test
    void testAddDataSuccess() throws Exception {
        Mockito.when(itemService.add(mockItem(false))).thenReturn(mockItem(true));
        this.mockMvc.perform(post("/api/v1/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJsonItem(true)))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testAddDataValidationFailure() throws Exception {
        Mockito.when(itemService.add(mockItem(false))).thenReturn(mockItem(true));
        this.mockMvc.perform(post("/api/v1/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void testFetchItem() throws Exception {
        Mockito.when(itemService.fetch(1L)).thenReturn(mockItem(true));
        this.mockMvc.perform(get("/api/v1/item/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }


    private Item mockItem(boolean forUpdate){
        return Item.builder()
                .brandName("Apple")
                .category("Mobile")
                .name("iPhone")
                .price(1000)
                .quantity(100)
                .id(forUpdate?1L:null)
                .build();
    }

    String getJsonItem(boolean forUpdate){
        ObjectMapper mapper=new ObjectMapper();
        String data="";
        try {
            data=mapper.writeValueAsString(mockItem(forUpdate));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return data;
    }
}
