package com.avadamedia.USAINUA.repositories;

import com.avadamedia.USAINUA.entity.AdditionalService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdditionalServicesRepository extends JpaRepository<AdditionalService, Long> {
    Optional<AdditionalService> findById(Long id);
    Optional<AdditionalService> findByName(String name);
    List<AdditionalService> findAll();
    void deleteById(Long id);
}
