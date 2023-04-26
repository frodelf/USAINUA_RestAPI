package com.avadamedia.USAINUA;

import com.avadamedia.USAINUA.controllers.MainController;
import com.avadamedia.USAINUA.entity.AdditionalServices;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class MainServiceTest {
    @Autowired
    private MainController mainController;


    @Test
    void purchaseAndDeliveryApproximatePrice() {
        Assertions.assertEquals(1085, mainController.purchaseAndDeliveryApproximatePrice("plane", new ArrayList<AdditionalServices>(), 100, 350));
    }

    @Test
    void deliveryApproximatePrice() {
        Assertions.assertEquals(1050, mainController.deliveryApproximatePrice("plane", new ArrayList<AdditionalServices>(), 100));
    }
}