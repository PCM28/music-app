package com.nter.pierre.carrillo_springboot_fundamentals.content.auth.domain.repository;

import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findUserEntityByUsername(String username);
}
