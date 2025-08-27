package com.nter.pierre.carrillo_springboot_fundamentals.content.auth.application.impl;

import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.application.service.AuthService;
import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.domain.entity.Role;
import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.domain.entity.UserEntity;
import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.domain.enums.RoleEnum;
import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.domain.repository.RoleRepository;
import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.domain.repository.UserEntityRepository;
import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.infrastructure.controller.auth.dto.AuthCreateUserRequest;
import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.infrastructure.controller.auth.dto.AuthLoginRequest;
import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.infrastructure.controller.auth.dto.AuthResponse;
import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.infrastructure.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticateManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private RoleRepository roleRepository;

    //Registrar usuario
    @Override
    public AuthResponse register(AuthCreateUserRequest createRoleRequest) {

        String username = createRoleRequest.username().trim();
        String password = createRoleRequest.password();

        // 1. Verifica si el username ya existe
        if(userEntityRepository.findUserEntityByUsername(username).isPresent()) throw new IllegalArgumentException("Username already exists");

        // 2. Busca en BD
        List<RoleEnum> rolesRequest = createRoleRequest.roleRequest().roleListName();
        Set<Role> roleEntityList = new HashSet<>(roleRepository.findByRoleEnumIn(rolesRequest));

        // 3. Verifica si está vacío
        if (roleEntityList.isEmpty()) throw new IllegalArgumentException("The roles specified do not exist.");

        // 4. Crea el usuario
        UserEntity userEntity = UserEntity.builder().username(username).password(passwordEncoder.encode(password)).roles(roleEntityList).isEnabled(true).accountNoLocked(true).accountNoExpired(true).credentialNoExpired(true).build();
        UserEntity userSaved = userEntityRepository.save(userEntity);

        // 5. Construye authorities (ROLE_ + permisos) del saved
        Set<SimpleGrantedAuthority> authorities = new LinkedHashSet<>();

        userSaved.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));
        userSaved.getRoles().stream().flatMap(role -> role.getPermissionList().stream()).forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getName())));

        // 6. JWT Principal = username (no UserEntity)
        Authentication authentication = new UsernamePasswordAuthenticationToken(userSaved.getUsername(), null, authorities);
        String accessToken = jwtUtils.createToken(authentication);

        AuthResponse authResponse = new AuthResponse(username, "User created successfully", accessToken, true);
        return authResponse;
    }

    //DONE LOGIN
    @Override
    public AuthResponse login(AuthLoginRequest authLoginRequest) {

        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        Authentication authentication = authenticateManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        String accessToken = jwtUtils.createToken(authentication);
        AuthResponse authResponse = new AuthResponse(username, "User logged succesfully", accessToken, true);
        return authResponse;
    }
}