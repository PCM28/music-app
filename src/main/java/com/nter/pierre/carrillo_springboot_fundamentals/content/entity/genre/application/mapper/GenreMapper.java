package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.genre.application.mapper;

import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.genre.domain.entity.Genre;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.genre.infrastructure.controller.dto.output.GenreArtistsDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.genre.infrastructure.controller.dto.input.GenreInputDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.genre.infrastructure.controller.dto.output.GenreOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    GenreOutputDto genreToGenreOutputDto(Genre genre);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "artists", ignore = true)
    Genre genreInputDtoToGenre(GenreInputDto genreInputDto);

    @Mapping(target = "genre_id", source = "id")
    @Mapping(target = "genre_name", source = "name")
    @Mapping(target = "genre_description", source = "description")
    @Mapping(target = "genre_artists", source = "artists")
    GenreArtistsDto genreToGenreArtistsDto(Genre genre);
}
