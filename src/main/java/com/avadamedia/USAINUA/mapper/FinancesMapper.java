package com.avadamedia.USAINUA.mapper;

import com.avadamedia.USAINUA.entity.Finances;
import com.avadamedia.USAINUA.models.FinancesDTO;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface FinancesMapper {
    FinancesDTO toDto(Finances finances);
    List<FinancesDTO> toDtoList(List<Finances> finances);
    Finances toEntity(FinancesDTO financesDTO);
    List<Finances> toEntityList(List<FinancesDTO> financesDTO);
}
