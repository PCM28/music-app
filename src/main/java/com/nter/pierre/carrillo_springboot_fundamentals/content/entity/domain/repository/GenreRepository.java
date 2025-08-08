package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.domain.repository;

import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.domain.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {}
