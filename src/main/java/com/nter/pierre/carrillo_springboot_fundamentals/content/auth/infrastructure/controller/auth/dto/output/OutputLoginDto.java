package com.nter.pierre.carrillo_springboot_fundamentals.content.auth.infrastructure.controller.auth.dto.output;

import java.util.List;

public record OutputLoginDto(
        String type,
        String token,
        String refreshToken,
        List<String> authorities,
        String username,
        String userType
) {}
