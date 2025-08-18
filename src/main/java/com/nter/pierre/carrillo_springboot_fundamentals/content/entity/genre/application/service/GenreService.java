package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.genre.application.service;

import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.genre.infrastructure.controller.dto.output.GenreArtistsDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.genre.infrastructure.controller.dto.input.GenreInputDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.genre.infrastructure.controller.dto.output.GenreOutputDto;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenreService {
    GenreOutputDto addGenre(GenreInputDto genreInputDto);
    List<GenreOutputDto> getAllGenres(Pageable pageable);
    GenreOutputDto getGenreById(Long id);
    GenreOutputDto updateGenre(Long id, GenreInputDto genreInputDto);
    void deleteGenreById(Long id) throws BadRequestException;

    void addArtistToGenre(Long artist_id, Long genre_id);

    List<GenreArtistsDto> getAllGenreArtists(Pageable pageable);
    GenreArtistsDto getGenreArtistsById(Long id);
}