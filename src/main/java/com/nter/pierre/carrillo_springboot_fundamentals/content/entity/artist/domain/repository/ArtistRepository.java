package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.artist.domain.repository;

import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.artist.domain.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {}
