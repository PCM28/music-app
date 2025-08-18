package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.artist.infrastructure.controller.dto.output;

import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.album.infrastructure.controller.dto.output.AlbumOutputDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.genre.infrastructure.controller.dto.output.GenreOutputDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtistAlbumsDto {
    private Long artist_id;
    private String artist_name;
    private GenreOutputDto artist_genre;
    private List<AlbumOutputDto> artist_albums;
}
