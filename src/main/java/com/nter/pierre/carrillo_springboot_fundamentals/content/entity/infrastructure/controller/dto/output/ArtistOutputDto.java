package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto.output;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtistOutputDto {
    private Long id;
    private String name;
}
