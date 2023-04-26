package com.avadamedia.USAINUA.mapper;

import com.avadamedia.USAINUA.entity.CreditCards;
import com.avadamedia.USAINUA.models.CreditCardDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CreditCardMapper {
    CreditCardDTO toDto(CreditCards creditCards);
    List<CreditCardDTO> toDtoList(List<CreditCards> creditCardsList);
    CreditCards toEntity(CreditCardDTO creditCardDTO);
    List<CreditCards> toEntityList(List<CreditCardDTO> creditCardDTOList);
}
