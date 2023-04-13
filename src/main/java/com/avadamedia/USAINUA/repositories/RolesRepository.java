package com.avadamedia.USAINUA.repositories;

import com.avadamedia.USAINUA.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Long> {
    @Override
    Optional<Roles> findById(Long id);
}
