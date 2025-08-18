package com.nter.pierre.carrillo_springboot_fundamentals.content.auth.domain.repository;

import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.domain.entity.UserJpa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserJpa, Long> {
    Optional<UserJpa> findByName(String name);
}
