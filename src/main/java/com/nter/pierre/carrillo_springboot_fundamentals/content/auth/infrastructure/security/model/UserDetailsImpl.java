package com.nter.pierre.carrillo_springboot_fundamentals.content.auth.infrastructure.security.model;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@Builder
public class UserDetailsImpl implements UserDetails {
    public enum UserType { DATABASE, LDAP }

    private final String username;
    private final Collection<? extends GrantedAuthority> authorities;
    private final UserType userType;

    @Override public String getPassword() { return null; }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}
