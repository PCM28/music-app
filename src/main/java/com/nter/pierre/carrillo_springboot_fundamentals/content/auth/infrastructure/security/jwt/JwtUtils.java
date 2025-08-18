package com.nter.pierre.carrillo_springboot_fundamentals.content.auth.infrastructure.security.jwt;

import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.infrastructure.security.model.UserDetailsImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class JwtUtils {

    private final SecretKey key;
    private final long accessMs;
    private final long refreshExtraMs;

    public JwtUtils(@Value("${jwt.secret}") String secret,
                    @Value("${jwt.expiration-ms:900000}") long accessMs,
                    @Value("${jwt.refresh-extra-ms:600000}") long refreshExtraMs) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessMs = accessMs;
        this.refreshExtraMs = refreshExtraMs;
    }

    public String generateJwtToken(Authentication authentication, boolean refreshToken) {
        var principal = (UserDetailsImpl) authentication.getPrincipal();
        var roles = principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        long now = System.currentTimeMillis();
        long ttl = refreshToken ? accessMs + refreshExtraMs : accessMs;

        return Jwts.builder()
                .subject(principal.getUsername())
                .claim("roles", roles)
                .issuedAt(new Date(now))
                .expiration(new Date(now + ttl))
                .signWith(key, Jwts.SIG.HS256)
                .compact();
    }

    public Optional<UserDetailsImpl> validateJwtToken(String jwt) {
        try {
            var claims = Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt).getPayload();
            String username = claims.getSubject();
            @SuppressWarnings("unchecked")
            var roles = (List<String>) claims.get("roles");
            var authorities = roles.stream()
                    .map(r -> r.startsWith("ROLE_") ? r : "ROLE_" + r)
                    .map(org.springframework.security.core.authority.SimpleGrantedAuthority::new)
                    .toList();

            var user = UserDetailsImpl.builder()
                    .username(username)
                    .authorities(authorities)
                    .userType(UserDetailsImpl.UserType.DATABASE)
                    .build();

            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
