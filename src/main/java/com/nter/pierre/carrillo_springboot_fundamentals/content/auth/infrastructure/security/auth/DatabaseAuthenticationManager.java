package com.nter.pierre.carrillo_springboot_fundamentals.content.auth.infrastructure.security.auth;

import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.domain.repository.UserRepository;
import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.infrastructure.security.model.UserDetailsImpl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseAuthenticationManager implements AuthenticationManager {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DatabaseAuthenticationManager(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String raw = (String) authentication.getCredentials();

        var user = userRepository.findByName(username)
                .orElseThrow(() -> new BadCredentialsException("User not found"));

        if (!Boolean.TRUE.equals(user.getEnabled()) || !passwordEncoder.matches(raw, user.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }

        var authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
        var principal = UserDetailsImpl.builder()
                .username(user.getName())
                .authorities(authorities)
                .userType(UserDetailsImpl.UserType.DATABASE)
                .build();

        return new UsernamePasswordAuthenticationToken(principal, null, authorities);
    }
}