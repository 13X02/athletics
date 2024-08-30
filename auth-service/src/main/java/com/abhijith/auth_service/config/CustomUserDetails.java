package com.abhijith.auth_service.config;

import com.abhijith.auth_service.entity.UserCredential;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private String id;
    private String username;
    private String password;
    private List<GrantedAuthority> authorities = new ArrayList<>();

    public CustomUserDetails(UserCredential userCredential) {
        this.id = userCredential.getId(); // Added id
        this.username = userCredential.getUsername();
        this.password = userCredential.getPassword();
        this.authorities.add(new SimpleGrantedAuthority(userCredential.getRole().name()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // Getter for id
    public String getId() {
        return id;
    }

    // Getter for role
    public String getRole() {
        return authorities.stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("ROLE_USER");
    }
}
