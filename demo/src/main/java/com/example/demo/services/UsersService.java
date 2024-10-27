package com.example.demo.services;

import com.example.demo.entity.JobSeekerProfile;
import com.example.demo.entity.RecruiterProfile;
import com.example.demo.entity.Users;
import com.example.demo.repository.JobSeekerProfileRepositories;
import com.example.demo.repository.RecruiterProfileRepositories;
import com.example.demo.repository.UsersRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    private final JobSeekerProfileRepositories jobSeekerRepositories;
    private final RecruiterProfileRepositories recruiterProfileRepositories;
    private final UsersRepositories usersRepositories;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersService(UsersRepositories usersRepositories, PasswordEncoder passwordEncoder, JobSeekerProfileRepositories jobSeekerRepositories, RecruiterProfileRepositories recruiterProfileRepositories) {
        this.usersRepositories = usersRepositories;
        this.passwordEncoder = passwordEncoder;
        this.jobSeekerRepositories = jobSeekerRepositories;
        this.recruiterProfileRepositories = recruiterProfileRepositories;
    }



    public List<Users> getAllUsers (){
        return usersRepositories.findAll();
    }

    public Users addUsers (Users users){

        users.setActive(true);
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        users.setRegistrationDate(new Date(System.currentTimeMillis()));
        Users savedUser = usersRepositories.save(users);
        int userTypeId =  users.getUserTypeId().getUserTypeId();
        if(userTypeId == 1){
                recruiterProfileRepositories.save(new RecruiterProfile(savedUser));
        }
        else{
            System.out.println(savedUser);
            jobSeekerRepositories.save(new JobSeekerProfile(savedUser));
        }
       return savedUser;
    }

    public Optional<Users> finByEmail(String email){

        return usersRepositories.findByEmail(email);
    }
}
