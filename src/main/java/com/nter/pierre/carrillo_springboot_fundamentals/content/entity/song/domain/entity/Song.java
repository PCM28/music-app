package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.song.domain.entity;

import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.album.domain.entity.Album;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.playlist.domain.entity.Playlist;
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
public class Song {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer duration;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;

    @ManyToMany(mappedBy = "songs")
    private List<Playlist> playlists;
}
