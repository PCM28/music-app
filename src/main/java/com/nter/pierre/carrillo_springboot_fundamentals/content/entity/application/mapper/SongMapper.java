package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.application.mapper;

import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.domain.entity.Song;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto.SongPlaylistsDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto.input.SongInputDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto.output.SongOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SongMapper {
    SongOutputDto songToSongOutputDto(Song song);
    Song songInputDtoToSong(SongInputDto songInputDto);

    @Mapping(target = "song_id", source = "id")
    @Mapping(target = "song_name", source = "name")
    @Mapping(target = "song_duration", source = "duration")
    @Mapping(target = "song_album", source = "album")
    @Mapping(target = "song_playlists", source = "playlists")
    SongPlaylistsDto songToSongPlaylistsDto(Song song);
}
