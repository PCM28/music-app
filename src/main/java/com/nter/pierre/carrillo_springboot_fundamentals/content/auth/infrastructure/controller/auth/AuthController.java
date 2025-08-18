package com.nter.pierre.carrillo_springboot_fundamentals.content.auth.infrastructure.controller.auth;

import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.infrastructure.controller.auth.dto.input.InputLoginDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.infrastructure.controller.auth.dto.output.OutputLoginDto;
import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.infrastructure.security.jwt.JwtUtils;
import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.infrastructure.security.model.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<OutputLoginDto> login(@RequestBody InputLoginDto body) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(body.username(), body.password())
        );

        String token   = jwtUtils.generateJwtToken(authentication, false);
        String refresh = jwtUtils.generateJwtToken(authentication, true);

        var principal = (UserDetailsImpl) authentication.getPrincipal();
        var roles = principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        return ResponseEntity.ok(new OutputLoginDto(
                "Bearer", token, refresh, roles, principal.getUsername(), principal.getUserType().name()
        ));
    }
}
