package com.avadamedia.USAINUA.mapper;

import com.avadamedia.USAINUA.entity.Orders;
import com.avadamedia.USAINUA.models.OrderDTO;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {
    public OrderDTO toDto(Orders orders) {
        if ( orders == null ) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setTransport( orders.getTransport() );
        orderDTO.setDescription( orders.getDescription() );
        orderDTO.setLink( orders.getLink() );
        orderDTO.setTrackNumber( orders.getTrackNumber() );
        if ( orders.getWeight() != null ) {
            orderDTO.setWeight( orders.getWeight() );
        }
        if ( orders.getPrice() != null ) {
            orderDTO.setPrice( orders.getPrice() );
        }
        orderDTO.setNumber( orders.getNumber() );
        orderDTO.setOnlyDelivery( orders.isOnlyDelivery() );

        return orderDTO;
    }
    public List<OrderDTO> toDtoList(List<Orders> orders) {
        if ( orders == null ) {
            return null;
        }

        List<OrderDTO> list = new ArrayList<OrderDTO>( orders.size() );
        for ( Orders order : orders ) {
            list.add( toDto( order ) );
        }

        return list;
    }
    public Orders toEntity(OrderDTO orderDTO) {
        if ( orderDTO == null ) {
            return null;
        }

        Orders order = new Orders();

        order.setTransport( orderDTO.getTransport() );
        order.setDescription( orderDTO.getDescription() );
        order.setLink( orderDTO.getLink() );
        order.setTrackNumber( orderDTO.getTrackNumber() );
        order.setWeight( orderDTO.getWeight() );
        order.setPrice( orderDTO.getPrice() );
        order.setNumber( orderDTO.getNumber() );
        order.setOnlyDelivery( orderDTO.isOnlyDelivery() );

        return order;
    }
    public List<Orders> toEntityList(List<OrderDTO> orderDTO) {
        if ( orderDTO == null ) {
            return null;
        }

        List<Orders> list = new ArrayList<Orders>( orderDTO.size() );
        for ( OrderDTO orderDTO1 : orderDTO ) {
            list.add( toEntity( orderDTO1 ) );
        }

        return list;
    }
}
