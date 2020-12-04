package com.example.musespringapi.controller;

import com.example.musespringapi.domain.User;
import com.example.musespringapi.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public void userPost(String userId) {
	   System.out.println("userId="+ userId);
	   
	   userService.insertUserId(userId);
    }
   
   @GetMapping("/users")
   public User findByUserId(String userId){
	   System.out.println(userId);
	   User userDetailbyId = userService.findByUserId(userId);
	  
	   return userDetailbyId; 
	   
   }
   
   @PostMapping("/userDetail")
   @ResponseStatus(HttpStatus.CREATED)
   public void addUserDetail (User user) {
	   System.out.println(user);
	   userService.insertUser(user);
   }
}
