package com.nter.pierre.carrillo_springboot_fundamentals.content.auth.infrastructure.security.config;

import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.infrastructure.security.auth.DatabaseAuthenticationManager;
import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.infrastructure.security.exception.RestAuthenticationEntryPoint;
import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.infrastructure.security.jwt.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfigDatabase extends WebSecurityConfigAbstract {

    public WebSecurityConfigDatabase(JwtFilter jwtFilter,
                                     RestAuthenticationEntryPoint entryPoint) {
        super(jwtFilter, entryPoint);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        super.securityFilterChainPrivate(http);
        return http.build();
    }
}
