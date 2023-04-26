package com.avadamedia.USAINUA.services;

import com.avadamedia.USAINUA.entity.Orders;

public interface OrdersService {
    void save(Orders orders);
    Orders getById(long id);
}
