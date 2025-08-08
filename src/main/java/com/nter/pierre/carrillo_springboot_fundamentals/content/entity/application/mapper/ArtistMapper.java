package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.application.mapper;

import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.domain.entity.Artist;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto.ArtistAlbumsDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto.input.ArtistInputDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto.output.ArtistOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArtistMapper {
    ArtistOutputDto artistToArtistOutputDto(Artist artist);
    Artist artistInputDtoToArtist(ArtistInputDto artistInputDto);

    @Mapping(target = "artist_id", source = "id")
    @Mapping(target = "artist_name", source = "name")
    @Mapping(target = "artist_genre", source = "genre")
    @Mapping(target = "artist_albums", source = "albums")
    ArtistAlbumsDto artistToArtistAlbumsDto(Artist artist);
}
