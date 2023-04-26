package com.avadamedia.USAINUA.mapper;

import com.avadamedia.USAINUA.entity.Shops;
import com.avadamedia.USAINUA.models.ShopDTO;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface ShopMapper {
    ShopDTO toDto(Shops shops);
    List<ShopDTO> toDtoList(List<Shops> shops);
    Shops toEntity(ShopDTO shopDTO);
    List<Shops> toEntityList(List<ShopDTO> shopDTO);
}
