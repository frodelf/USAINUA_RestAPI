package com.avadamedia.USAINUA.mapper;

import com.avadamedia.USAINUA.entity.Products;
import com.avadamedia.USAINUA.models.ProductDTO;

import java.util.ArrayList;
import java.util.List;
public class ProductMapper {
    public ProductDTO toDto(Products products) {
        if ( products == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setName( products.getName() );
        productDTO.setPrice( products.getPrice() );
        productDTO.setType( products.getType() );
        productDTO.setLink( products.getLink() );
        productDTO.setImageName( products.getImageName() );

        return productDTO;
    }
    public List<ProductDTO> toDtoList(List<Products> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductDTO> list = new ArrayList<ProductDTO>( products.size() );
        for ( Products product : products ) {
            list.add( toDto( product ) );
        }

        return list;
    }
    public Products toEntity(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        Products product = new Products();

        product.setName( productDTO.getName() );
        product.setPrice( productDTO.getPrice() );
        product.setType( productDTO.getType() );
        product.setLink( productDTO.getLink() );
        product.setImageName( productDTO.getImageName() );

        return product;
    }
    public List<Products> toEntityList(List<ProductDTO> productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        List<Products> list = new ArrayList<Products>( productDTO.size() );
        for ( ProductDTO productDTO1 : productDTO ) {
            list.add( toEntity( productDTO1 ) );
        }

        return list;
    }
}