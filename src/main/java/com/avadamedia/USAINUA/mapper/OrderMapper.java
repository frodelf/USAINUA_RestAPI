package com.avadamedia.USAINUA.mapper;

import com.avadamedia.USAINUA.entity.Order;
import com.avadamedia.USAINUA.models.OrderDTO;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {
    public OrderDTO toDto(Order orders) {
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
    public List<OrderDTO> toDtoList(List<Order> orders) {
        if ( orders == null ) {
            return null;
        }

        List<OrderDTO> list = new ArrayList<OrderDTO>( orders.size() );
        for ( Order order : orders ) {
            list.add( toDto( order ) );
        }

        return list;
    }
    public Order toEntity(OrderDTO orderDTO) {
        if ( orderDTO == null ) {
            return null;
        }

        Order order = new Order();

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
    public List<Order> toEntityList(List<OrderDTO> orderDTO) {
        if ( orderDTO == null ) {
            return null;
        }

        List<Order> list = new ArrayList<Order>( orderDTO.size() );
        for ( OrderDTO orderDTO1 : orderDTO ) {
            list.add( toEntity( orderDTO1 ) );
        }

        return list;
    }
}
