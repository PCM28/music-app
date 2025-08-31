package com.example.demo.country;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<CountryJpa, Long> {
    Optional<CountryJpa> findByName(String name);
}
