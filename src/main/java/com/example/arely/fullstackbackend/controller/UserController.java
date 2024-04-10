package com.example.arely.fullstackbackend.controller;

import com.example.arely.fullstackbackend.model.User;
import com.example.arely.fullstackbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/user")
    User newUser(@RequestBody User newUser){
        return userRepository.save(newUser);
    }

    @GetMapping(path="/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    

}
