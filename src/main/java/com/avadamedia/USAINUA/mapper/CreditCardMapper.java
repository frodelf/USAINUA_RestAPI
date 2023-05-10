package com.avadamedia.USAINUA.mapper;

import com.avadamedia.USAINUA.entity.CreditCard;
import com.avadamedia.USAINUA.models.CreditCardDTO;

import java.util.ArrayList;
import java.util.List;

public class CreditCardMapper {
    public CreditCardDTO toDto(CreditCard creditCard) {
        if ( creditCard == null ) {
            return null;
        }

        CreditCardDTO creditCardDTO = new CreditCardDTO();

        creditCardDTO.setCvv( creditCard.getCvv() );
        creditCardDTO.setNumber( creditCard.getCardsNumber() );
        creditCardDTO.setPeriod( creditCard.getValidityPeriod() );

        return creditCardDTO;
    }
    public List<CreditCardDTO> toDtoList(List<CreditCard> creditCardList) {
        if ( creditCardList == null ) {
            return null;
        }

        List<CreditCardDTO> list = new ArrayList<CreditCardDTO>( creditCardList.size() );
        for ( CreditCard creditCard : creditCardList) {
            list.add( toDto( creditCard ) );
        }

        return list;
    }
    public CreditCard toEntity(CreditCardDTO creditCardDTO) {
        if ( creditCardDTO == null ) {
            return null;
        }

        CreditCard creditCard = new CreditCard();

        creditCard.setCvv( creditCardDTO.getCvv() );
        creditCard.setCardsNumber(creditCardDTO.getNumber());
        creditCard.setValidityPeriod(creditCardDTO.getPeriod());

        return creditCard;
    }
    public List<CreditCard> toEntityList(List<CreditCardDTO> creditCardDTOList) {
        if ( creditCardDTOList == null ) {
            return null;
        }

        List<CreditCard> list = new ArrayList<CreditCard>( creditCardDTOList.size() );
        for ( CreditCardDTO creditCardDTO : creditCardDTOList ) {
            list.add( toEntity( creditCardDTO ) );
        }

        return list;
    }
}

