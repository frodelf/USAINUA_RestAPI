package com.avadamedia.USAINUA.controllers;

import com.avadamedia.USAINUA.entity.AdditionalServices;
import com.avadamedia.USAINUA.repositories.ProductsRepository;
import com.avadamedia.USAINUA.repositories.ShopsRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class MainControllerTest {
    @Autowired
    private MainController mainController;


    @Test
    void purchaseAndDeliveryApproximatePrice() {
        if(1085 == mainController.purchaseAndDeliveryApproximatePrice("plane", new ArrayList<AdditionalServices>(), 100, 350));
        else throw new RuntimeException();
    }

    @Test
    void deliveryApproximatePrice() {
        if(1050 == mainController.deliveryApproximatePrice("plane", new ArrayList<AdditionalServices>(), 100));
        else throw new RuntimeException();
    }
}