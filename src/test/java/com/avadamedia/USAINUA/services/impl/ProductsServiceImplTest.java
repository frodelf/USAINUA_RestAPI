package com.avadamedia.USAINUA.services.impl;

import com.avadamedia.USAINUA.entity.Product;
import com.avadamedia.USAINUA.repositories.ProductsRepository;
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
class ProductsServiceImplTest {
    @MockBean
    private ProductsRepository productsRepository;
    @Autowired
    private ProductsServiceImpl productsService;
    @Test
    public void save() {
        Product product = new Product();
        productsService.save(product);
        verify(productsRepository, times(1)).save(product);
    }

    @Test
    void getAll() {
        List<Product> products = Arrays.asList(new Product(), new Product());
        when(productsRepository.findAll()).thenReturn(products);
        List<Product> result = productsService.getAll();
        assertEquals(products, result);
    }

    @Test
    void getByLink() {
        Product product = new Product();
        product.setLink("link");
        when(productsRepository.findByLink("link")).thenReturn(Optional.of(product));
        Product result = productsService.getByLink("link");
        assertEquals(product, result);
    }

    @Test
    void deleteById() {
        productsService.deleteById(1L);
        verify(productsRepository, times(1)).deleteById(1L);
    }

    @Test
    void getById() {
        Product product = new Product();
        product.setId(1L);
        when(productsRepository.findById(1L)).thenReturn(Optional.of(product));
        Product result = productsService.getById(1L);
        assertEquals(product, result);
    }
}