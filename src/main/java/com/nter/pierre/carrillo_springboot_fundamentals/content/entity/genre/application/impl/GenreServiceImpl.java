package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.genre.application.impl;

import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.genre.application.service.GenreService;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.genre.application.mapper.GenreMapper;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.artist.domain.entity.Artist;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.genre.domain.entity.Genre;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.artist.domain.repository.ArtistRepository;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.genre.domain.repository.GenreRepository;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.genre.infrastructure.controller.dto.output.GenreArtistsDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.genre.infrastructure.controller.dto.input.GenreInputDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.genre.infrastructure.controller.dto.output.GenreOutputDto;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private GenreMapper genreMapper;

    @Autowired
    private ArtistRepository artistRepository;

    @Override
    public GenreOutputDto addGenre(GenreInputDto genreInputDto) {
        Genre genreEntity = genreMapper.genreInputDtoToGenre(genreInputDto);
        Genre savedGenre = genreRepository.save(genreEntity);
        return genreMapper.genreToGenreOutputDto(savedGenre);
    }

    @Override
    public List<GenreOutputDto> getAllGenres(Pageable pageable) {
        return genreRepository.findAll(pageable).stream().map(genreMapper::genreToGenreOutputDto).toList();
    }

    @Override
    public GenreOutputDto getGenreById(Long id) {
        Genre genre = genreRepository.findById(id).orElseThrow();
        return genreMapper.genreToGenreOutputDto(genre);
    }

    @Override
    public GenreOutputDto updateGenre(Long id, GenreInputDto genreInputDto) {
        Genre existingGenre = genreRepository.findById(id).orElseThrow();
        existingGenre.setId(id);
        existingGenre.setName(genreInputDto.getName());
        existingGenre.setDescription(genreInputDto.getDescription());
        return genreMapper.genreToGenreOutputDto(genreRepository.save(existingGenre));
    }

    @Override
    public void deleteGenreById(Long id) throws BadRequestException {
        Genre genre = genreRepository.findById(id).orElseThrow();
        if(!genre.getArtists().isEmpty()) throw new BadRequestException("Cannot delete this genre because it has associated artists");
        genreRepository.deleteById(id);
    }

    @Override
    public void addArtistToGenre(Long artist_id, Long genre_id) {
        Artist artist = artistRepository.findById(artist_id).orElseThrow();
        Genre genre = genreRepository.findById(genre_id).orElseThrow();

        artist.setGenre(genre);
        genre.getArtists().add(artist);

        artistRepository.save(artist);
        genreRepository.save(genre);
    }

    @Override
    public List<GenreArtistsDto> getAllGenreArtists(Pageable pageable) {
        return genreRepository.findAll(pageable).map(genreMapper::genreToGenreArtistsDto).toList();
    }

    @Override
    public GenreArtistsDto getGenreArtistsById(Long id) {
        return genreMapper.genreToGenreArtistsDto(genreRepository.findById(id).orElseThrow());
    }

}