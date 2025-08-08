package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.application.mapper;

import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.domain.entity.Playlist;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto.PlaylistSongsDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto.input.PlaylistInputDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto.output.PlaylistOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlaylistMapper {
    PlaylistOutputDto playlistToPlaylistOutputDto(Playlist playlist);
    Playlist playlistInputDtoToPlaylist(PlaylistInputDto playlistInputDto);

    @Mapping(target = "playlist_id", source = "id")
    @Mapping(target = "playlist_name", source = "name")
    @Mapping(target = "playlist_description", source = "description")
    @Mapping(target = "playlist_songs", source = "songs")
    PlaylistSongsDto playlistToPlaylistSongsDto(Playlist playlist);
}
