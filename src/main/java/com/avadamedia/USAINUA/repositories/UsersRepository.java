package com.avadamedia.USAINUA.repositories;

import com.avadamedia.USAINUA.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findById(Long id);

    Optional<Users> findByEmail(String email);
    List<Users> findByIsManIsTrue();
    List<Users> findByIsManIsFalse();
}
