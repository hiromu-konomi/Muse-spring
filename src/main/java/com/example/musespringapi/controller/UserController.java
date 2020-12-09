package com.example.musespringapi.controller;

import com.example.musespringapi.domain.User;
import com.example.musespringapi.service.UserService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/users")

  public User findByUserId(String userNum) {
    System.out.println(userNum);
    User userDetailbyId = userService.findByUserId(userNum);
    return userDetailbyId;

  }

  @PostMapping("/userDetail")
  @ResponseStatus(HttpStatus.CREATED)
  public void addUserDetail(@RequestBody User user) {

    userService.insertUser(user);
  }

  @PostMapping("/users")
  @ResponseStatus(HttpStatus.CREATED)
  public void userPost(@RequestBody String userNum) {

    System.out.println("userId=" + userNum);
    userService.insertUserId(userNum);
  }

  // @RequestMapping(path = "/postform", method = RequestMethod.GET)
  @GetMapping("/postform")
  public String show(String userNum) {

    List<User> userList = userService.findByUserNum(userNum);

    User user = new User();
    user.setUserName(userList.get(0).getUserName());
    System.out.println(user.getUserName());
    return user.getUserName();

  }

}
