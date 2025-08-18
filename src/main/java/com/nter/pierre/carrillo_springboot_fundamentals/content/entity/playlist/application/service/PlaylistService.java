package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.playlist.application.service;


import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.playlist.infrastructure.controller.dto.output.PlaylistSongsDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.playlist.infrastructure.controller.dto.input.PlaylistInputDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.playlist.infrastructure.controller.dto.output.PlaylistOutputDto;
import org.springframework.data.domain.Pageable;

import java.util.LinkedHashSet;
import java.util.List;

public interface PlaylistService {
    PlaylistOutputDto addPlaylist(PlaylistInputDto playlistInputDto);
    List<PlaylistOutputDto> getAllPlaylists(Pageable pageable);
    PlaylistOutputDto getPlaylistById(Long id);
    PlaylistOutputDto updatePlaylist(Long id, PlaylistInputDto playlistInputDto);
    void deletePlaylistById(Long id);

    LinkedHashSet<PlaylistSongsDto> getAllPlaylistSongs(Pageable pageable);
    PlaylistSongsDto getPlaylistSongsById(Long id);
}
