package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.album.domain.entity;

import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.artist.domain.entity.Artist;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.song.domain.entity.Song;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Album {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private LocalDate releaseDate;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @OneToMany(mappedBy = "album")
    private List<Song> songs;
}
