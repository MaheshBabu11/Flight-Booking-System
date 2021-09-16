package com.mycompany.flightbookingsystem.controller;

import com.mycompany.flightbookingsystem.model.User;
import com.mycompany.flightbookingsystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User newUser=userService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

}
