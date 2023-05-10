package com.avadamedia.USAINUA.repositories;

import com.avadamedia.USAINUA.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Role, Long> {
    @Override
    Optional<Role> findById(Long id);
}
