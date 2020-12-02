package com.example.musespringapi.controller;

import com.example.musespringapi.domain.User;
import com.example.musespringapi.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {
    
    @Autowired
    UserService userService;

   @PostMapping("/users")
   @ResponseStatus(HttpStatus.CREATED)
    public void userPost(@RequestBody User userId) {
	   System.out.println(userId);
       userService.insertUserId(userId);
    }
}
