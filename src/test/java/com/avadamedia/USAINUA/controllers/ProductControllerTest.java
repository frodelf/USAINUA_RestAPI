package com.avadamedia.USAINUA.controllers;

import com.avadamedia.USAINUA.entity.Product;
import com.avadamedia.USAINUA.enums.Type;
import com.avadamedia.USAINUA.repositories.ProductsRepository;
import com.avadamedia.USAINUA.repositories.ShopsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ProductsRepository productsRepository;
    @Test
    @WithMockUser
    void getAllProducts() throws Exception {
        Product product1 = new Product(1L, "product 1", 12.0, Type.Sport.name(), "http://qwerty1", "image 1");
        Product product2 = new Product(2L, "product 2", 120.0, Type.Another.name(), "http://qwerty2", "image 2");

        when(productsRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        mockMvc.perform(get("/products/get-product/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("product 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("product 2"));
    }
}