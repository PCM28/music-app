package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto;

import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto.output.ArtistOutputDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto.output.SongOutputDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlbumSongsDto {
    private Long album_id;
    private String album_name;
    private LocalDate album_releaseDate;
    private ArtistOutputDto album_artist;
    private List<SongOutputDto> album_songs;
}
