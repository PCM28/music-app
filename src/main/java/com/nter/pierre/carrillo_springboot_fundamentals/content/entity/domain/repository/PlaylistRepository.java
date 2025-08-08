package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.domain.repository;

import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.domain.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {}
