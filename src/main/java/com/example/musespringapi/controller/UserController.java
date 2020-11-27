package com.example.musespringapi.controller;

import com.example.musespringapi.domain.User;
import com.example.musespringapi.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public List<User> userPost(@RequestBody List<User> userList) {
        List<User> result = userRepository.saveAll(userList);
        return result;
    }
}
