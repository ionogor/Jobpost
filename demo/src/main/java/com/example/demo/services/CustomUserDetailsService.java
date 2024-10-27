package com.example.demo.services;

import com.example.demo.entity.Users;
import com.example.demo.repository.UsersRepositories;
import com.example.demo.util.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepositories usersRepositories;

    @Autowired
    public CustomUserDetailsService(UsersRepositories usersRepositories) {
        this.usersRepositories = usersRepositories;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users couldNotFoundTheUser = usersRepositories.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Could not found the user"));
        return new CustomUserDetails(couldNotFoundTheUser);
    }
}
