package com.avadamedia.USAINUA.services.impl;

import com.avadamedia.USAINUA.entity.CreditCard;
import com.avadamedia.USAINUA.repositories.CreditCardsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class CreditCardsServiceImplTest {
    @Mock
    private CreditCardsRepository creditCardsRepository;
    @InjectMocks
    private CreditCardsServiceImpl creditCardsService;
    @Test
    void save() {
        CreditCard creditCard = new CreditCard();
        creditCard.setCvv("333");
        creditCardsService.save(creditCard);
        verify(creditCardsRepository, times(1)).save(creditCard);
    }
}