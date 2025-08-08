package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.application.impl;

import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.application.SongService;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.application.mapper.SongMapper;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.domain.entity.Playlist;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.domain.entity.Song;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.domain.repository.PlaylistRepository;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.domain.repository.SongRepository;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto.SongPlaylistsDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto.input.SongInputDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto.output.SongOutputDto;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private SongMapper songMapper;

    @Autowired
    private PlaylistRepository playlistRepository;

    @Override
    public SongOutputDto addSong(SongInputDto songInputDto) {
        Song songEntity = songMapper.songInputDtoToSong(songInputDto);
        Song savedSong = songRepository.save(songEntity);
        return songMapper.songToSongOutputDto(savedSong);
    }

    @Override
    public List<SongOutputDto> getAllSongs(Pageable pageable) {
        return songRepository.findAll(pageable).stream().map(songMapper::songToSongOutputDto).toList();
    }

    @Override
    public SongOutputDto getSongById(Long id) {
        Song songEntity = songRepository.findById(id).orElseThrow();
        return songMapper.songToSongOutputDto(songEntity);
    }

    @Override
    public SongOutputDto updateSong(Long id, SongInputDto songInputDto) {
        Song existingSong = songRepository.findById(id).orElseThrow();
        existingSong.setId(id);
        existingSong.setName(songInputDto.getName());
        existingSong.setDuration(songInputDto.getDuration());
        return songMapper.songToSongOutputDto(songRepository.save(existingSong));
    }

    @Override
    public void deleteSongById(Long id) throws BadRequestException {
        Song song = songRepository.findById(id).orElseThrow();
        if(!song.getPlaylists().isEmpty()) throw new BadRequestException("Cannot delete this song because it has associated playlists");
        songRepository.deleteById(id);
    }

    @Override
    public void addPlaylistToSong(Long playlist_id, Long song_id) {
        Playlist playlist = playlistRepository.findById(playlist_id).orElseThrow();
        Song song = songRepository.findById(song_id).orElseThrow();

        playlist.getSongs().add(song);
        playlistRepository.save(playlist);
    }

    @Override
    public LinkedHashSet<SongPlaylistsDto> getAllSongPlaylists(Pageable pageable) {
        return new LinkedHashSet<>(songRepository.findAll(pageable).map(songMapper::songToSongPlaylistsDto).toList());
    }

    @Override
    public SongPlaylistsDto getSongPlaylistsById(Long id) {
        return songMapper.songToSongPlaylistsDto(songRepository.findById(id).orElseThrow());
    }

}