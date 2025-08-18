package com.nter.pierre.carrillo_springboot_fundamentals.content.auth.infrastructure.security.init;

import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.domain.entity.UserJpa;
import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.domain.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SeedUsersConfig {
    @Bean
    CommandLineRunner seed(UserRepository repo, PasswordEncoder encoder) {
        return args -> repo.findByName("admin").orElseGet(() -> {
            var u = new UserJpa();
            u.setName("admin");
            u.setPassword(encoder.encode("admin")); // BCrypt
            u.setRole("ADMIN");
            u.setEnabled(true);
            return repo.save(u);
        });
    }
}