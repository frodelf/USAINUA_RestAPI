package com.avadamedia.USAINUA.repositories;

import com.avadamedia.USAINUA.entity.Finances;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancesRepository extends JpaRepository<Finances, Long> {
}
