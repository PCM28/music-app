package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.application;

import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto.SongPlaylistsDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto.input.SongInputDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto.output.SongOutputDto;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Pageable;

import java.util.LinkedHashSet;
import java.util.List;

public interface SongService {
    SongOutputDto addSong(SongInputDto songInputDto);
    List<SongOutputDto> getAllSongs(Pageable pageable);
    SongOutputDto getSongById(Long id);
    SongOutputDto updateSong(Long id, SongInputDto songInputDto);
    void deleteSongById(Long id) throws BadRequestException;

    void addPlaylistToSong(Long playlist_id, Long song_id);

    LinkedHashSet<SongPlaylistsDto> getAllSongPlaylists(Pageable pageable);
    SongPlaylistsDto getSongPlaylistsById(Long id);
}
