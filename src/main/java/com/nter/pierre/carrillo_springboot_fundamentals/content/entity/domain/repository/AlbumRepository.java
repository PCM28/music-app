package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.domain.repository;

import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.domain.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {}
