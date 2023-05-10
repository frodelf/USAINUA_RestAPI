package com.avadamedia.USAINUA.repositories;

import com.avadamedia.USAINUA.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShopsRepository extends JpaRepository<Shop, Long> {
    List<Shop> findAll();
    Optional<Shop> findByLink(String link);
    Optional<Shop> findById(Long id);
    void deleteById(Long id);
}
