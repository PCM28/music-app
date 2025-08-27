package com.nter.pierre.carrillo_springboot_fundamentals.content.auth.infrastructure.controller.auth.dto;

import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.domain.enums.RoleEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.List;

public record AuthCreateRoleRequest(
        @Size(max = 3, message = "The user cannot have more than 3 roles")
        @NotEmpty(message = "At least one role is required") List<RoleEnum> roleListName) {}