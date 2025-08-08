package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.application;

import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto.AlbumSongsDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto.input.AlbumInputDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto.output.AlbumOutputDto;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AlbumService {
    AlbumOutputDto addAlbum(AlbumInputDto albumInputDto);
    List<AlbumOutputDto> getAllAlbums(Pageable pageable);
    AlbumOutputDto getAlbumById(Long id);
    AlbumOutputDto updateAlbum(Long id, AlbumInputDto albumInputDto);
    void deleteAlbumById(Long id) throws BadRequestException;

    void addSongToAlbum(Long song_id, Long album_id);

    List<AlbumSongsDto> getAllAlbumSongs(Pageable pageable);
    AlbumSongsDto getAlbumSongsById(Long id);
}
