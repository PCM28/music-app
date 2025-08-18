package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.genre.domain.entity;

import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.artist.domain.entity.Artist;
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
public class Genre {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "genre")
    private List<Artist> artists;
}
