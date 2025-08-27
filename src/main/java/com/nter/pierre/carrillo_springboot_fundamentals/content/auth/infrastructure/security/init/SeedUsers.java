package com.nter.pierre.carrillo_springboot_fundamentals.content.auth.infrastructure.security.init;


import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.domain.entity.Role;
import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.domain.entity.UserEntity;

import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.domain.enums.RoleEnum;
import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.domain.repository.RoleRepository;
import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.domain.repository.UserEntityRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Configuration
public class SeedUsers {

    @Bean
    @Transactional
    CommandLineRunner seed(UserEntityRepository userRepo,
                           RoleRepository roleRepo,
                           PasswordEncoder encoder) {
        return args -> {
            // 1) Asegura que existen los roles base
            Role admin   = ensureRole(roleRepo, RoleEnum.ADMIN);
            Role user    = ensureRole(roleRepo, RoleEnum.USER);
            Role invited = ensureRole(roleRepo, RoleEnum.INVITED);
            Role dev     = ensureRole(roleRepo, RoleEnum.DEVELOPER);

            // 2) Crea usuario admin si no existe
            userRepo.findUserEntityByUsername("admin").orElseGet(() -> {
                UserEntity u = UserEntity.builder()
                        .username("admin")
                        .password(encoder.encode("admin"))
                        .isEnabled(true)
                        .accountNoExpired(true)
                        .accountNoLocked(true)
                        .credentialNoExpired(true)
                        .build();

                // asigna roles ya gestionados
                u.getRoles().addAll(List.of(admin, user, dev));
                return userRepo.save(u);
            });
        };
    }

    /** Crea el rol si no existe y lo devuelve gestionado */
    private Role ensureRole(RoleRepository repo, RoleEnum re) {
        return repo.findByRoleEnum(re)
                .orElseGet(() -> repo.save(Role.builder().roleEnum(re).build()));
    }
}