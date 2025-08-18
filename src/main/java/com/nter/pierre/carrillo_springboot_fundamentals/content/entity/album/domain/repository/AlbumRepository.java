package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.album.domain.repository;

import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.album.domain.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {}
