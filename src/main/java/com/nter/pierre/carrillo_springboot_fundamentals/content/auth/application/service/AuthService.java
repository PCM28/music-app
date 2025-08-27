package com.nter.pierre.carrillo_springboot_fundamentals.content.auth.application.service;

import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.infrastructure.controller.auth.dto.AuthCreateUserRequest;
import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.infrastructure.controller.auth.dto.AuthLoginRequest;
import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.infrastructure.controller.auth.dto.AuthResponse;

public interface AuthService {
    AuthResponse register(AuthCreateUserRequest createRoleRequest); //CreateUser
    AuthResponse login(AuthLoginRequest authLoginRequest);
}