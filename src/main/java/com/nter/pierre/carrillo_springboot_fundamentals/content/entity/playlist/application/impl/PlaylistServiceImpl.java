package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.playlist.application.impl;

import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.playlist.application.service.PlaylistService;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.playlist.application.mapper.PlaylistMapper;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.playlist.domain.entity.Playlist;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.playlist.domain.repository.PlaylistRepository;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.playlist.infrastructure.controller.dto.output.PlaylistSongsDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.playlist.infrastructure.controller.dto.input.PlaylistInputDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.playlist.infrastructure.controller.dto.output.PlaylistOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;

@Service
public class PlaylistServiceImpl implements PlaylistService {

    @Autowired
    private PlaylistMapper playlistMapper;

    @Autowired
    private PlaylistRepository playlistRepository;

    @Override
    public PlaylistOutputDto addPlaylist(PlaylistInputDto playlistInputDto) {
         Playlist playlistEntity = playlistMapper.playlistInputDtoToPlaylist(playlistInputDto);
         Playlist savedPlaylist = playlistRepository.save(playlistEntity);
        return playlistMapper.playlistToPlaylistOutputDto(savedPlaylist);
    }

    @Override
    public List<PlaylistOutputDto> getAllPlaylists(Pageable pageable) {
        return playlistRepository.findAll(pageable).stream().map(playlistMapper::playlistToPlaylistOutputDto).toList();
    }

    @Override
    public PlaylistOutputDto getPlaylistById(Long id) {
        Playlist playlistEntity = playlistRepository.findById(id).orElseThrow();
        return playlistMapper.playlistToPlaylistOutputDto(playlistEntity);
    }

    @Override
    public PlaylistOutputDto updatePlaylist(Long id, PlaylistInputDto playlistInputDto) {
        Playlist existingPlaylist = playlistRepository.findById(id).orElseThrow();
        existingPlaylist.setId(id);
        existingPlaylist.setName(playlistInputDto.getName());
        existingPlaylist.setDescription(playlistInputDto.getDescription());
        return playlistMapper.playlistToPlaylistOutputDto(playlistRepository.save(existingPlaylist));
    }

    @Override
    public void deletePlaylistById(Long id) {
        playlistRepository.findById(id).orElseThrow();
        playlistRepository.deleteById(id);
    }

    @Override
    public LinkedHashSet<PlaylistSongsDto> getAllPlaylistSongs(Pageable pageable) {
        return new LinkedHashSet<>(playlistRepository.findAll(pageable).map(playlistMapper::playlistToPlaylistSongsDto).toList());
    }

    @Override
    public PlaylistSongsDto getPlaylistSongsById(Long id) {
        return playlistMapper.playlistToPlaylistSongsDto(playlistRepository.findById(id).orElseThrow());
    }

}