package com.avadamedia.USAINUA.mapper;

import com.avadamedia.USAINUA.entity.CreditCards;
import com.avadamedia.USAINUA.models.CreditCardDTO;

import java.util.ArrayList;
import java.util.List;

public class CreditCardMapper {
    public CreditCardDTO toDto(CreditCards creditCards) {
        if ( creditCards == null ) {
            return null;
        }

        CreditCardDTO creditCardDTO = new CreditCardDTO();

        creditCardDTO.setCvv( creditCards.getCvv() );
        creditCardDTO.setNumber( creditCards.getCardsNumber() );
        creditCardDTO.setPeriod( creditCards.getValidityPeriod() );

        return creditCardDTO;
    }
    public List<CreditCardDTO> toDtoList(List<CreditCards> creditCardsList) {
        if ( creditCardsList == null ) {
            return null;
        }

        List<CreditCardDTO> list = new ArrayList<CreditCardDTO>( creditCardsList.size() );
        for ( CreditCards creditCard : creditCardsList ) {
            list.add( toDto( creditCard ) );
        }

        return list;
    }
    public CreditCards toEntity(CreditCardDTO creditCardDTO) {
        if ( creditCardDTO == null ) {
            return null;
        }

        CreditCards creditCard = new CreditCards();

        creditCard.setCvv( creditCardDTO.getCvv() );
        creditCard.setCardsNumber(creditCardDTO.getNumber());
        creditCard.setValidityPeriod(creditCardDTO.getPeriod());

        return creditCard;
    }
    public List<CreditCards> toEntityList(List<CreditCardDTO> creditCardDTOList) {
        if ( creditCardDTOList == null ) {
            return null;
        }

        List<CreditCards> list = new ArrayList<CreditCards>( creditCardDTOList.size() );
        for ( CreditCardDTO creditCardDTO : creditCardDTOList ) {
            list.add( toEntity( creditCardDTO ) );
        }

        return list;
    }
}

