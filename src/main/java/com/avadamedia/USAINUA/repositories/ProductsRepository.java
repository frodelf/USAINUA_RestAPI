package com.avadamedia.USAINUA.repositories;

import com.avadamedia.USAINUA.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository extends JpaRepository<Products, Long> {
    List<Products> findAll();
    List<Products> findTop3ByOrderByIdDesc();
    Optional<Products> findByLink(String link);
    Optional<Products> findById(Long id);
    void deleteById(Long id);
}
