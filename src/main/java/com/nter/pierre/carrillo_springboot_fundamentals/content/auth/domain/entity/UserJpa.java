package com.nter.pierre.carrillo_springboot_fundamentals.content.auth.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;          // username

    @Column(nullable = false)
    private String password;      // BCrypt

    @Column(nullable = false)
    private String role;          // e.g. ADMIN / USER

    @Column(nullable = false)
    private Boolean enabled = true;
}
