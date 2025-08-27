package com.nter.pierre.carrillo_springboot_fundamentals.content.auth.infrastructure.controller.auth;

import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.application.service.AuthService;
import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.infrastructure.controller.auth.dto.AuthCreateUserRequest;
import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.infrastructure.controller.auth.dto.AuthLoginRequest;
import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.infrastructure.controller.auth.dto.AuthResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody AuthCreateUserRequest userRequest){
        return new ResponseEntity<>(authService.register(userRequest), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthLoginRequest userRequest){
        return new ResponseEntity<>(authService.login(userRequest), HttpStatus.OK);
    }
}
