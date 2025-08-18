package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.genre.infrastructure.controller.dto.output;

import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.artist.infrastructure.controller.dto.output.ArtistOutputDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenreArtistsDto {
    private Long genre_id;
    private String genre_name;
    private String genre_description;
    private List<ArtistOutputDto> genre_artists;
}
