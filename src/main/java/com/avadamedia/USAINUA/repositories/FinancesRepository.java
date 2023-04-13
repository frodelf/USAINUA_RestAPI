package com.avadamedia.USAINUA.repositories;

import com.avadamedia.USAINUA.models.Finances;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancesRepository extends JpaRepository<Finances, Long> {
}
