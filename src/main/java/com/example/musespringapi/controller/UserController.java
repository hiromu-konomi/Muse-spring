package com.example.musespringapi.controller;

import com.example.musespringapi.domain.User;
import com.example.musespringapi.service.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	System.out.println(user.getHireDate());
    userService.insertUser(user);
  }

  @PostMapping("/users")
  @ResponseStatus(HttpStatus.CREATED)
  public void userPost(@RequestBody String userNum) {

    System.out.println("userId=" + userNum);
    userService.insertUserId(userNum);
  }
  
  
  @GetMapping("/userInfo")
  public List<User> findByHireDate(String hireDate) throws ParseException{
	  System.out.println(hireDate);
	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	  Date date = sdf.parse(hireDate);
	  System.out.println(date);
	  List<User> users = userService.findByHireDate(date);
	  System.out.println(userService.findByHireDate(date));
	  return users;
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
