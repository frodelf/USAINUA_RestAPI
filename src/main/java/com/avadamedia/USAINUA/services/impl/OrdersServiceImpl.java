package com.avadamedia.USAINUA.services.impl;

import com.avadamedia.USAINUA.entity.Orders;
import com.avadamedia.USAINUA.repositories.OrdersRepository;
import com.avadamedia.USAINUA.services.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {
    private final OrdersRepository ordersRepository;

    public void save(Orders orders){ordersRepository.save(orders);}
    public Orders getById(long id){return ordersRepository.findById(id).get();}
}
