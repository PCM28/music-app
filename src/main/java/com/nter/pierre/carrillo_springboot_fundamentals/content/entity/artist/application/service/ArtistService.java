package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.artist.application.service;

import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.artist.infrastructure.controller.dto.output.ArtistAlbumsDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.artist.infrastructure.controller.dto.input.ArtistInputDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.artist.infrastructure.controller.dto.output.ArtistOutputDto;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArtistService {
    ArtistOutputDto addArtist(ArtistInputDto artistInputDto);
    List<ArtistOutputDto> getAllArtists(Pageable pageable);
    ArtistOutputDto getArtistById(Long id);
    ArtistOutputDto updateArtist(Long id, ArtistInputDto artistInputDto);
    void deleteArtistById(Long id) throws BadRequestException;

    void addAlbumToArtist(Long album_id, Long artist_id);

    List<ArtistAlbumsDto> getAllArtistAlbums(Pageable pageable);
    ArtistAlbumsDto getArtistAlbumsById(Long id);
}
