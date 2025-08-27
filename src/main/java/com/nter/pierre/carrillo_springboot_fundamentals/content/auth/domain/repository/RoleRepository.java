package com.nter.pierre.carrillo_springboot_fundamentals.content.auth.domain.repository;

import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.domain.entity.Role;
import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.domain.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleEnum(RoleEnum roleEnum);
    List<Role> findByRoleEnumIn(List<RoleEnum> roleEnums);
}
