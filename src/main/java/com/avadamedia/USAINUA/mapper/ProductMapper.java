package com.avadamedia.USAINUA.mapper;

import com.avadamedia.USAINUA.entity.Product;
import com.avadamedia.USAINUA.models.ProductDTO;

import java.util.ArrayList;
import java.util.List;
public class ProductMapper {
    public ProductDTO toDto(Product products) {
        if ( products == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setName( products.getName() );
        productDTO.setPrice( products.getPrice() );
        productDTO.setType( products.getType() );
        productDTO.setLink( products.getLink() );
        productDTO.setImageName("/USAINUA_Admin/uploads/products/"+products.getImageName() );

        return productDTO;
    }
    public List<ProductDTO> toDtoList(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductDTO> list = new ArrayList<ProductDTO>( products.size() );
        for ( Product product : products ) {
            list.add( toDto( product ) );
        }

        return list;
    }
    public Product toEntity(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setName( productDTO.getName() );
        product.setPrice( productDTO.getPrice() );
        product.setType( productDTO.getType() );
        product.setLink( productDTO.getLink() );
        product.setImageName( productDTO.getImageName() );

        return product;
    }
    public List<Product> toEntityList(List<ProductDTO> productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        List<Product> list = new ArrayList<Product>( productDTO.size() );
        for ( ProductDTO productDTO1 : productDTO ) {
            list.add( toEntity( productDTO1 ) );
        }

        return list;
    }
}