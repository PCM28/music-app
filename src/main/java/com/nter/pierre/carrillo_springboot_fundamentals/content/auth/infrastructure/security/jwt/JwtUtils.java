package com.nter.pierre.carrillo_springboot_fundamentals.content.auth.infrastructure.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

    private static final String CLAIM_AUTHORITIES = "authorities";
    private static final long EXP_MILLIS = 30 * 60 * 1000; // 30 minutos

    @Value("${security.jwt.key.private}")
    private String privateKey;

    @Value("${security.jwt.user.generator}")
    private String userGenerator;

    private SecretKey hmacKey() {
        return Keys.hmacShaKeyFor(privateKey.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(Authentication authentication) {
        String username = authentication.getPrincipal().toString();
        String authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Date now =  new Date();
        Date exp = new Date(now.getTime() + EXP_MILLIS);

        return Jwts.builder()
                .setIssuer(userGenerator)
                .setSubject(username)
                .claim(CLAIM_AUTHORITIES, authorities)
                .setIssuedAt(now)
                .setNotBefore(now)
                .setExpiration(exp)
                .setId(UUID.randomUUID().toString())
                .signWith(hmacKey())
                .compact();
    }

    public Jws<Claims> validateToken(String token) throws JwtException {
        try {
            return Jwts.parser()
                    .verifyWith(hmacKey())
                    .requireIssuer(userGenerator)
                    .build()
                    .parseSignedClaims(token);
        } catch (JwtException exception) {
            throw new JwtException("Token invalid, not Authorized: ", exception);
        }
    }

    public String extractUsername(Jws<Claims> decodedJWT){
        return decodedJWT.getPayload().getSubject();
    }

    public String extractAuthorities(Jws<Claims> jws) {
        return jws.getPayload().get(CLAIM_AUTHORITIES, String.class);
    }
}
