package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.application.impl;

import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.application.ArtistService;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.application.mapper.ArtistMapper;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.domain.entity.Album;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.domain.entity.Artist;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.domain.repository.AlbumRepository;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.domain.repository.ArtistRepository;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto.ArtistAlbumsDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto.input.ArtistInputDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto.output.ArtistOutputDto;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private ArtistMapper artistMapper;

    @Autowired
    private AlbumRepository albumRepository;

    @Override
    public ArtistOutputDto addArtist(ArtistInputDto artistInputDto) {
        Artist artistEntity = artistMapper.artistInputDtoToArtist(artistInputDto);
        Artist savedArtist = artistRepository.save(artistEntity);
        return artistMapper.artistToArtistOutputDto(savedArtist);
    }

    @Override
    public List<ArtistOutputDto> getAllArtists(Pageable pageable) {
        return artistRepository.findAll(pageable).stream().map(artistMapper::artistToArtistOutputDto).toList();
    }

    @Override
    public ArtistOutputDto getArtistById(Long id) {
        Artist artist = artistRepository.findById(id).orElseThrow();
        return artistMapper.artistToArtistOutputDto(artist);
    }

    @Override
    public ArtistOutputDto updateArtist(Long id, ArtistInputDto artistInputDto) {
        Artist existingArtist = artistRepository.findById(id).orElseThrow();
        existingArtist.setId(id);
        existingArtist.setName(artistInputDto.getName());
        return artistMapper.artistToArtistOutputDto(artistRepository.save(existingArtist));
    }

    @Override
    public void deleteArtistById(Long id) throws BadRequestException {
        Artist artist = artistRepository.findById(id).orElseThrow();
        if(!artist.getAlbums().isEmpty()) throw new BadRequestException("Cannot delete this artist because it has associated albums");
        artistRepository.deleteById(id);
    }

    @Override
    public void addAlbumToArtist(Long album_id, Long artist_id) {
        Album album = albumRepository.findById(album_id).orElseThrow();
        Artist artist = artistRepository.findById(artist_id).orElseThrow();

        album.setArtist(artist);
        artist.getAlbums().add(album);

        albumRepository.save(album);
        artistRepository.save(artist);
    }

    @Override
    public List<ArtistAlbumsDto> getAllArtistAlbums(Pageable pageable) {
        return artistRepository.findAll(pageable).map(artistMapper::artistToArtistAlbumsDto).toList();
    }

    @Override
    public ArtistAlbumsDto getArtistAlbumsById(Long id) {
        return artistMapper.artistToArtistAlbumsDto(artistRepository.findById(id).orElseThrow());
    }

}