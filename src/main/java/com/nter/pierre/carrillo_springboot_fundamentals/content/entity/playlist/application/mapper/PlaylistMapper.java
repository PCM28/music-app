package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.playlist.application.mapper;

import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.playlist.domain.entity.Playlist;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.playlist.infrastructure.controller.dto.output.PlaylistSongsDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.playlist.infrastructure.controller.dto.input.PlaylistInputDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.playlist.infrastructure.controller.dto.output.PlaylistOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlaylistMapper {
    PlaylistOutputDto playlistToPlaylistOutputDto(Playlist playlist);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "songs", ignore = true)
    Playlist playlistInputDtoToPlaylist(PlaylistInputDto playlistInputDto);

    @Mapping(target = "playlist_id", source = "id")
    @Mapping(target = "playlist_name", source = "name")
    @Mapping(target = "playlist_description", source = "description")
    @Mapping(target = "playlist_songs", source = "songs")
    PlaylistSongsDto playlistToPlaylistSongsDto(Playlist playlist);
}
