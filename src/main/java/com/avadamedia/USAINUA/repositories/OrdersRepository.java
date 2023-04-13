package com.avadamedia.USAINUA.repositories;

import com.avadamedia.USAINUA.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
