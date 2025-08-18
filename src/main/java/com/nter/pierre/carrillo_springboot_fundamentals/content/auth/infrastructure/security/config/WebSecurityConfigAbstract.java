package com.nter.pierre.carrillo_springboot_fundamentals.content.auth.infrastructure.security.config;


import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.infrastructure.security.jwt.JwtFilter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class WebSecurityConfigAbstract {

    protected final JwtFilter jwtFilter;
    protected final AuthenticationEntryPoint exceptionHandling;

    protected HttpSecurity securityFilterChainPrivate(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(h -> h.authenticationEntryPoint(exceptionHandling));
        return httpSecurity;
    }

}