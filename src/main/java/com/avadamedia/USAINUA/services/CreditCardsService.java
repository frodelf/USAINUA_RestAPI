package com.avadamedia.USAINUA.services;

import com.avadamedia.USAINUA.models.CreditCards;
import com.avadamedia.USAINUA.repositories.CreditCardsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditCardsService {
    private final CreditCardsRepository creditCardsRepository;
    public void save(CreditCards creditCards){creditCardsRepository.save(creditCards);}
}
