package com.avadamedia.USAINUA.repositories;

import com.avadamedia.USAINUA.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardsRepository extends JpaRepository<CreditCard, Long> {
}
