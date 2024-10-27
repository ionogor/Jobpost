package com.example.demo.util;

import com.example.demo.entity.Users;
import com.example.demo.entity.UsersType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    public CustomUserDetails(Users users) {
        this.users = users;
    }

    private final Users users;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        UsersType userTypeId = users.getUserTypeId();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userTypeId.getUserTypeName()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getEmail();
    }
}
