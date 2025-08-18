package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.album.infrastructure.controller.dto.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlbumOutputDto {
    private Long id;
    private String name;
    private LocalDate releaseDate;
}
