package com.example.musespringapi.controller;

import com.example.musespringapi.domain.User;
import com.example.musespringapi.service.UserService;

import java.util.ArrayList;
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

  // 入社日で表示する
  @GetMapping("/userInfo")
  public List<User> findByHireDate(String userNum) {
    System.out.println("userNum=" + userNum);
    User user = userService.findByUserId(userNum);
    List<User> userList = new ArrayList<User>();

    if (user == null) {
      return null;
    } else {
      List<User> userListByHireDate = userService.findByHireDate(user.getHireDate());

      if (userListByHireDate == null) {
        return null;
      } else {
        for (User users : userListByHireDate) {
          System.out.println(users.getUserNum());
          System.out.println(userNum);
          if (!(users.getUserNum().equals(userNum))) {
            userList.add(users);
          }
        }
      }
    }

    System.out.println(userList.size());

    return userList;
  }

  // @RequestMapping(path = "/postform", method = RequestMethod.GET)
  @GetMapping("/postform")
  public String show(String userNum) {

    String userName = userService.userNameFindByUserNum(userNum);

    User user = new User();
    user.setUserName(userName);
    System.out.println(userName);
    return userName;

  }

}
