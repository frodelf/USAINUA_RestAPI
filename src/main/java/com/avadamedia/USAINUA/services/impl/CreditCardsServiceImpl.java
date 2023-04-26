package com.avadamedia.USAINUA.services.impl;

import com.avadamedia.USAINUA.entity.CreditCards;
import com.avadamedia.USAINUA.repositories.CreditCardsRepository;
import com.avadamedia.USAINUA.services.CreditCardsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditCardsServiceImpl implements CreditCardsService {
    private final CreditCardsRepository creditCardsRepository;
    public void save(CreditCards creditCards){creditCardsRepository.save(creditCards);}
}
