package com.nter.pierre.carrillo_springboot_fundamentals.content.entity.infrastructure.controller.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistInputDto {
    @Size(min = 3, max = 20, message = "Name must be between 3 and 20 characters")
    @NotBlank(message = "Name is required")
    private String name;

    @Size(min = 3, max = 20, message = "Description must be between 3 and 20 characters")
    @NotBlank(message = "Description is required")
    private String description;
}
