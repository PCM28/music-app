package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto;

import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto.output.AlbumOutputDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto.output.PlaylistOutputDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SongPlaylistsDto {
    private Long song_id;
    private String song_name;
    private Integer song_duration;
    private AlbumOutputDto song_album;
    private LinkedHashSet<PlaylistOutputDto> song_playlists;
}
