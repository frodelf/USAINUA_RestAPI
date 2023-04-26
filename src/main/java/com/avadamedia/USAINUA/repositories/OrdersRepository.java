package com.avadamedia.USAINUA.repositories;

import com.avadamedia.USAINUA.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
