package com.avadamedia.USAINUA.services.impl;

import com.avadamedia.USAINUA.entity.Shop;
import com.avadamedia.USAINUA.repositories.ShopsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ShopsServiceImplTest {
    @MockBean
    private ShopsRepository shopsRepository;
    @Autowired
    private ShopsServiceImpl shopsService;

    @Test
    void save() {
        Shop shop = new Shop();
        shopsService.save(shop);
        verify(shopsRepository, times(1)).save(shop);
    }

    @Test
    void getAll() {
        List<Shop> shops = Arrays.asList(new Shop(), new Shop());
        when(shopsRepository.findAll()).thenReturn(shops);
        List<Shop> result = shopsService.getAll();
        assertEquals(shops, result);
    }

    @Test
    void getByLink() {
        Shop shop = new Shop();
        shop.setLink("link");
        when(shopsRepository.findByLink("link")).thenReturn(Optional.of(shop));
        Shop result = shopsService.getByLink("link");
        assertEquals(shop, result);
    }

    @Test
    void getById() {
        Shop shop = new Shop();
        shop.setId(1L);
        when(shopsRepository.findById(1L)).thenReturn(Optional.of(shop));
        Shop result = shopsService.getById(1L);
        assertEquals(shop, result);
    }

    @Test
    void deleteById() {
        shopsService.deleteById(1L);
        verify(shopsRepository, times(1)).deleteById(1L);
    }
}