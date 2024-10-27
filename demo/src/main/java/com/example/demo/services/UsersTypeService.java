package com.example.demo.services;

import com.example.demo.entity.Users;

import com.example.demo.entity.UsersType;
import com.example.demo.repository.UsersTypeRepositories;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersTypeService {

    private final UsersTypeRepositories usersTypeRepositories;

    public UsersTypeService(UsersTypeRepositories usersTypeRepositories) {
        this.usersTypeRepositories = usersTypeRepositories;
    }


    public List<UsersType> getAll()
    {
        return usersTypeRepositories.findAll();
    }
}
