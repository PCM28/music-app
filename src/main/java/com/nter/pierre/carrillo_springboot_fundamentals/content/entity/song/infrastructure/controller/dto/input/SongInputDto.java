package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.song.infrastructure.controller.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SongInputDto {
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "La duración en segundos obligatoria")
    @Positive(message = "La duración debe ser un número positivo")
    private Integer duration;
}
