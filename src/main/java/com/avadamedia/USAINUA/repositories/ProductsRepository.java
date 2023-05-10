package com.avadamedia.USAINUA.repositories;

import com.avadamedia.USAINUA.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository extends JpaRepository<Product, Long> {
    List<Product> findAll();
    List<Product> findTop3ByOrderByIdDesc();
    Optional<Product> findByLink(String link);
    Optional<Product> findById(Long id);
    void deleteById(Long id);
}
