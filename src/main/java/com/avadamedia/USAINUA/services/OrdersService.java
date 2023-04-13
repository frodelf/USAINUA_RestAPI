package com.avadamedia.USAINUA.services;

import com.avadamedia.USAINUA.models.Orders;
import com.avadamedia.USAINUA.repositories.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;

    public void save(Orders orders){ordersRepository.save(orders);}
}
