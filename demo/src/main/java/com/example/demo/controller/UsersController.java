package com.example.demo.controller;

import com.example.demo.entity.Users;
import com.example.demo.entity.UsersType;
import com.example.demo.services.UsersService;
import com.example.demo.services.UsersTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Controller
public class UsersController {

    private final UsersTypeService usersTypeService;
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersTypeService usersService, UsersService usersService1) {
        this.usersTypeService = usersService;
        this.usersService = usersService1;
    }

    @GetMapping("/register")
    public String register(Model model){

        List<UsersType> getAllUsers = usersTypeService.getAll();

        model.addAttribute("listTypeUsers",getAllUsers);
        model.addAttribute("user",new Users());
        return "register";
    }

    @PostMapping("/register/new")
    public String addUser(@Valid Users users,Model model){

        Optional<Users> optionalUsersEmail = usersService.finByEmail(users.getEmail());

        if(optionalUsersEmail.isPresent()){

            model.addAttribute("error", "Email it's exist, please enter new email.");

            return register(model);
        }

        usersService.finByEmail(users.getEmail());

        usersService.addUsers(users);
        return "/home";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
