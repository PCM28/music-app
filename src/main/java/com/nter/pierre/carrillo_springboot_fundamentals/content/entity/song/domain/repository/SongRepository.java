package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.song.domain.repository;

import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.song.domain.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {}
