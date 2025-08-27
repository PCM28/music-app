package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.song.application.mapper;

import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.song.domain.entity.Song;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.song.infrastructure.controller.dto.output.SongPlaylistsDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.song.infrastructure.controller.dto.input.SongInputDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.song.infrastructure.controller.dto.output.SongOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SongMapper {
    SongOutputDto songToSongOutputDto(Song song);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "album", ignore = true)
    @Mapping(target = "playlists", ignore = true)
    Song songInputDtoToSong(SongInputDto songInputDto);

    @Mapping(target = "song_id", source = "id")
    @Mapping(target = "song_name", source = "name")
    @Mapping(target = "song_duration", source = "duration")
    @Mapping(target = "song_album", source = "album")
    @Mapping(target = "song_playlists", source = "playlists")
    SongPlaylistsDto songToSongPlaylistsDto(Song song);
}
