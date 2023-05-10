package com.avadamedia.USAINUA.controllers;

import com.avadamedia.USAINUA.entity.AdditionalService;
import com.avadamedia.USAINUA.entity.Product;
import com.avadamedia.USAINUA.entity.Shop;
import com.avadamedia.USAINUA.models.ProductDTO;
import com.avadamedia.USAINUA.models.ShopDTO;
import com.avadamedia.USAINUA.repositories.ProductsRepository;
import com.avadamedia.USAINUA.repositories.ShopsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class MainControllerTest {
    @Autowired
    private MainController mainController;
    @MockBean
    private ProductsRepository productsRepository;
    @MockBean
    private ShopsRepository shopsRepository;
    @Test
    void purchaseAndDeliveryApproximatePrice() {
        Assertions.assertEquals(1085, mainController.purchaseAndDeliveryApproximatePrice("plane", new ArrayList<AdditionalService>(), 100, 350));
    }

    @Test
    void deliveryApproximatePrice() {
        Assertions.assertEquals(1050, mainController.deliveryApproximatePrice("plane", new ArrayList<AdditionalService>(), 100));
    }

    @Test
    void getAllProducts() {
        long id = 1L;
        List<Product> products = Arrays.asList(
                new Product(1L,"ball1", 10, "Sport", "http://asdf", "asdgfqweffwedfgs&df"),
                new Product(2L,"ball2", 11, "Another", "http://asdf", "dsdfgsxfhdhgfgh")
                );
        Page<Product> productsPage = new PageImpl<>(products, PageRequest.of(0, 2), products.size());

        when(productsRepository.findAll(PageRequest.of((int)(id-1), 2))).thenReturn(productsPage);

        List<ProductDTO> productDTOList = mainController.getAllProducts(id);

        assertEquals(2, productDTOList.size());
        assertEquals("ball1", productDTOList.get(0).getName());
        assertEquals(10, productDTOList.get(0).getPrice());
        assertEquals("Sport", productDTOList.get(0).getType());
        assertEquals("http://asdf", productDTOList.get(0).getLink());
        assertEquals("asdgfqweffwedfgs&df", productDTOList.get(0).getImageName());
        assertEquals("ball2", productDTOList.get(1).getName());
        assertEquals(11, productDTOList.get(1).getPrice());
        assertEquals("Another", productDTOList.get(1).getType());
        assertEquals("http://asdf", productDTOList.get(1).getLink());
        assertEquals("dsdfgsxfhdhgfgh", productDTOList.get(1).getImageName());
    }
    @Test
    void getAllShops() {
        long id = 1L;
        List<Shop> shops = Arrays.asList(
                new Shop(1L, "Shop1", "image1.png"),
                new Shop(2L, "Shop2", "image2.png")
        );
        Page<Shop> shopsPage = new PageImpl<>(shops, PageRequest.of(0, 2), shops.size());

        when(shopsRepository.findAll(PageRequest.of((int)(id-1), 2))).thenReturn(shopsPage);

        List<ShopDTO> shopDTOList = mainController.getAllShops(id);

        assertEquals(2, shopDTOList.size());
        assertEquals("Shop1", shopDTOList.get(0).getLink());
        assertEquals("image1.png", shopDTOList.get(0).getImageName());
        assertEquals("Shop2", shopDTOList.get(1).getLink());
        assertEquals("image2.png", shopDTOList.get(1).getImageName());
    }

}