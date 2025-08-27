package com.nter.pierre.carrillo_springboot_fundamentals.content.auth.domain.repository;

import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.domain.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {}
