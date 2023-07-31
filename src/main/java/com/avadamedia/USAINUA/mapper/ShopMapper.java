package com.avadamedia.USAINUA.mapper;

import com.avadamedia.USAINUA.entity.Shop;
import com.avadamedia.USAINUA.models.ShopDTO;

import java.util.ArrayList;
import java.util.List;
public class ShopMapper {
    public ShopDTO toDto(Shop shop) {
        if ( shop == null ) {
            return null;
        }

        ShopDTO shopDTO = new ShopDTO();

        shopDTO.setLink( shop.getLink() );
        shopDTO.setImageName("/USAINUA_Admin/uploads/shops/"+shop.getImageName());

        return shopDTO;
    }
    public List<ShopDTO> toDtoList(List<Shop> shops) {
        if ( shops == null ) {
            return null;
        }

        List<ShopDTO> list = new ArrayList<ShopDTO>( shops.size() );
        for ( Shop shop : shops ) {
            list.add( toDto( shop ) );
        }

        return list;
    }
    public Shop toEntity(ShopDTO shopDTO) {
        if ( shopDTO == null ) {
            return null;
        }

        Shop shop = new Shop();

        shop.setLink( shopDTO.getLink() );
        shop.setImageName( shopDTO.getImageName() );

        return shop;
    }
    public List<Shop> toEntityList(List<ShopDTO> shopDTO) {
        if ( shopDTO == null ) {
            return null;
        }

        List<Shop> list = new ArrayList<Shop>( shopDTO.size() );
        for ( ShopDTO shopDTO1 : shopDTO ) {
            list.add( toEntity( shopDTO1 ) );
        }

        return list;
    }
}
