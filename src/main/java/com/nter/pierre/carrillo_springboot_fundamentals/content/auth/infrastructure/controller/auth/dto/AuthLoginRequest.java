package com.nter.pierre.carrillo_springboot_fundamentals.content.auth.infrastructure.controller.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthLoginRequest(@NotBlank String username, @NotBlank String password) {}