package com.avadamedia.USAINUA.repositories;

import com.avadamedia.USAINUA.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);
    List<User> findByIsManIsTrue();
    List<User> findByIsManIsFalse();
}
