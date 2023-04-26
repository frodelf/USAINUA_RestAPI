package com.avadamedia.USAINUA.mapper;

import com.avadamedia.USAINUA.entity.Orders;
import com.avadamedia.USAINUA.models.OrderDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDTO toDto(Orders orders);
    List<OrderDTO> toDtoList(List<Orders> orders);
    Orders toEntity(OrderDTO orderDTO);
    List<Orders> toEntityList(List<OrderDTO> orderDTO);
}