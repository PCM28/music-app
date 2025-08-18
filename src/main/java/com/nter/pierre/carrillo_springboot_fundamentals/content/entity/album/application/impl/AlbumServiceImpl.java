package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.album.application.impl;

import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.album.application.service.AlbumService;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.album.application.mapper.AlbumMapper;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.album.domain.entity.Album;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.song.domain.entity.Song;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.album.domain.repository.AlbumRepository;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.song.domain.repository.SongRepository;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.album.infrastructure.controller.dto.output.AlbumSongsDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.album.infrastructure.controller.dto.input.AlbumInputDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.album.infrastructure.controller.dto.output.AlbumOutputDto;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private AlbumMapper albumMapper;

    @Autowired
    private SongRepository songRepository;

    @Override
    public AlbumOutputDto addAlbum(AlbumInputDto albumInputDto) {
        Album albumEntity = albumMapper.albumInputDtoToAlbum(albumInputDto);
        Album savedAlbum = albumRepository.save(albumEntity);
        return albumMapper.albumToAlbumOutputDto(savedAlbum);
    }

    @Override
    public List<AlbumOutputDto> getAllAlbums(Pageable pageable) {
        return albumRepository.findAll(pageable).stream().map(albumMapper::albumToAlbumOutputDto).toList();
    }

    @Override
    public AlbumOutputDto getAlbumById(Long id) {
        Album albumEntity = albumRepository.findById(id).orElseThrow();
        return albumMapper.albumToAlbumOutputDto(albumEntity);
    }

    @Override
    public AlbumOutputDto updateAlbum(Long id, AlbumInputDto albumInputDto) {
        Album existingAlbum = albumRepository.findById(id).orElseThrow();
        existingAlbum.setId(id);
        existingAlbum.setName(albumInputDto.getName());
        existingAlbum.setReleaseDate(albumInputDto.getReleaseDate());
        return albumMapper.albumToAlbumOutputDto(albumRepository.save(existingAlbum));
    }

    @Override
    public void deleteAlbumById(Long id) throws BadRequestException {
        Album album = albumRepository.findById(id).orElseThrow();
        if(!album.getSongs().isEmpty()) throw new BadRequestException("Cannot delete this album because it has associated songs");
        albumRepository.deleteById(id);
    }

    @Override
    public void addSongToAlbum(Long song_id, Long album_id) {
        Song song = songRepository.findById(song_id).orElseThrow();
        Album album = albumRepository.findById(album_id).orElseThrow();

        song.setAlbum(album);
        album.getSongs().add(song);

        songRepository.save(song);
        albumRepository.save(album);
    }

    @Override
    public List<AlbumSongsDto> getAllAlbumSongs(Pageable pageable) {
        return albumRepository.findAll(pageable).map(albumMapper::albumToAlbumSongsDto).toList();
    }

    @Override
    public AlbumSongsDto getAlbumSongsById(Long id) {
        return albumMapper.albumToAlbumSongsDto(albumRepository.findById(id).orElseThrow());
    }

}