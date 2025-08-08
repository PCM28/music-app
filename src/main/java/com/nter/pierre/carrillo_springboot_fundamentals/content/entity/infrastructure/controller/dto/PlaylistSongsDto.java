package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto;

import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto.output.SongOutputDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistSongsDto {
    private Long playlist_id;
    private String playlist_name;
    private String playlist_description;
    private LinkedHashSet<SongOutputDto> playlist_songs;
}