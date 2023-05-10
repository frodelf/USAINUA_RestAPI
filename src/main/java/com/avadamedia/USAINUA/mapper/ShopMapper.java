package com.avadamedia.USAINUA.mapper;

import com.avadamedia.USAINUA.entity.Shops;
import com.avadamedia.USAINUA.models.ShopDTO;

import java.util.ArrayList;
import java.util.List;
public class ShopMapper {
    public ShopDTO toDto(Shops shops) {
        if ( shops == null ) {
            return null;
        }

        ShopDTO shopDTO = new ShopDTO();

        shopDTO.setLink( shops.getLink() );
        shopDTO.setImageName( shops.getImageName() );

        return shopDTO;
    }
    public List<ShopDTO> toDtoList(List<Shops> shops) {
        if ( shops == null ) {
            return null;
        }

        List<ShopDTO> list = new ArrayList<ShopDTO>( shops.size() );
        for ( Shops shop : shops ) {
            list.add( toDto( shop ) );
        }

        return list;
    }
    public Shops toEntity(ShopDTO shopDTO) {
        if ( shopDTO == null ) {
            return null;
        }

        Shops shop = new Shops();

        shop.setLink( shopDTO.getLink() );
        shop.setImageName( shopDTO.getImageName() );

        return shop;
    }
    public List<Shops> toEntityList(List<ShopDTO> shopDTO) {
        if ( shopDTO == null ) {
            return null;
        }

        List<Shops> list = new ArrayList<Shops>( shopDTO.size() );
        for ( ShopDTO shopDTO1 : shopDTO ) {
            list.add( toEntity( shopDTO1 ) );
        }

        return list;
    }
}
