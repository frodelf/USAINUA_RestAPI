package com.avadamedia.USAINUA.repositories;

import com.avadamedia.USAINUA.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Order, Long> {
}
