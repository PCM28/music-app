package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.application.mapper;

import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.domain.entity.Album;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto.AlbumSongsDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto.input.AlbumInputDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto.output.AlbumOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AlbumMapper {
    AlbumOutputDto albumToAlbumOutputDto(Album album);
    Album albumInputDtoToAlbum(AlbumInputDto albumInputDto);

    @Mapping(target = "album_id", source = "id")
    @Mapping(target = "album_name", source = "name")
    @Mapping(target = "album_releaseDate", source = "releaseDate")
    @Mapping(target = "album_artist", source = "artist")
    @Mapping(target = "album_songs", source = "songs")
    AlbumSongsDto albumToAlbumSongsDto(Album album);
}
