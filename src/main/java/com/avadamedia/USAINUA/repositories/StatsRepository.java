package com.avadamedia.USAINUA.repositories;

import com.avadamedia.USAINUA.entity.Stats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatsRepository extends JpaRepository<Stats, Long> {
    List<Stats> findAll();
}
