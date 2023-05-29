package com.avadamedia.USAINUA.controllers;

import com.avadamedia.USAINUA.entity.Shop;
import com.avadamedia.USAINUA.mapper.ShopMapper;
import com.avadamedia.USAINUA.models.ShopDTO;
import com.avadamedia.USAINUA.repositories.ShopsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class ShopControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ShopsRepository shopsRepository;
    @Test
    @WithMockUser
    void getAllShops() throws Exception {
        Shop shop1 = new Shop();
        shop1.setId(1L);
        shop1.setImageName("Shop 1");
        shop1.setLink("https://www.youtube.com/1");

        Shop shop2 = new Shop();
        shop2.setId(2L);
        shop2.setImageName("Shop 2");
        shop2.setLink("https://www.youtube.com/2");
        List<Shop> mockShops = Arrays.asList(shop1, shop2);

        when(shopsRepository.findAll()).thenReturn(mockShops);
        mockMvc.perform(get("/shops/get-shop/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].link").value("https://www.youtube.com/1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].imageName").value("Shop 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].link").value("https://www.youtube.com/2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].imageName").value("Shop 2"));

    }
}