package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.playlist.domain.entity;

import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.song.domain.entity.Song;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Playlist {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;

    @ManyToMany
    private List<Song> songs;
}
