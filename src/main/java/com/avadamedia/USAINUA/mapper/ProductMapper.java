package com.avadamedia.USAINUA.mapper;

import com.avadamedia.USAINUA.entity.Products;
import com.avadamedia.USAINUA.entity.Shops;
import com.avadamedia.USAINUA.models.ProductDTO;
import com.avadamedia.USAINUA.models.ShopDTO;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toDto(Products products);
    List<ProductDTO> toDtoList(List<Products> products);
    Products toEntity(ProductDTO productDTO);
    List<Products> toEntityList(List<ProductDTO> productDTO);
}
