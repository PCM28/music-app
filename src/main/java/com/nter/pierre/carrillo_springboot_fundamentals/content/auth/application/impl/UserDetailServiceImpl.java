package com.nter.pierre.carrillo_springboot_fundamentals.content.auth.application.impl;

import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.domain.entity.UserEntity;
import com.nter.pierre.carrillo_springboot_fundamentals.content.auth.domain.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    //carga el UserEntity por username (normalizas con trim()),

    //arma el conjunto de authorities con ROLE_... + permisos,

    //y devuelve un User de Spring con los flags de cuenta.

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        String normalizedUsername = username.trim();

        UserEntity userEntity = userEntityRepository.findUserEntityByUsername(normalizedUsername).orElseThrow(() -> new UsernameNotFoundException("User: " + normalizedUsername + " doesn't exist."));

        Set<SimpleGrantedAuthority> authorityList = new LinkedHashSet<>();

        userEntity.getRoles().forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));
        userEntity.getRoles().stream().flatMap(role -> role.getPermissionList().stream()).forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));

        return new User(userEntity.getUsername(), userEntity.getPassword(), userEntity.isEnabled(), userEntity.isAccountNoExpired(), userEntity.isCredentialNoExpired(), userEntity.isAccountNoLocked(), authorityList);
    }
}