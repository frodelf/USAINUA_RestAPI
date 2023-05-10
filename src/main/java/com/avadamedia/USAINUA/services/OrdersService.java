package com.avadamedia.USAINUA.services;

import com.avadamedia.USAINUA.entity.Order;
import com.avadamedia.USAINUA.models.OrderDTO;

import java.util.List;

public interface OrdersService {
    void save(Order orders);
    Order getById(long id);
    void payOrder(Long id);
    void addOrder(OrderDTO orderDTO, List<Long> idAdditionalServices, Long idAddress);
}
