package com.example.musespringapi.controller;

import com.example.musespringapi.domain.User;
import com.example.musespringapi.repository.UserRepository;
import com.example.musespringapi.service.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
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
  
  
  //入社日で表示する
  @GetMapping("/userInfo")
  public List<User> findByHireDate(String userNum) {
	  
	  User user = userService.findByUserId(userNum);
	  List<User> userList = new ArrayList<User>();
	  
	  if(user == null) {
		  return null;
	  } else {
		  List<User> userListByHireDate = userService.findByHireDate(user.getHireDate());
		  
		  if(userListByHireDate == null) {
			  return null;
		  } else {
			  for(User users : userListByHireDate) {
				  System.out.println(users.getUserNum());
				  System.out.println(userNum);
				  if(!(users.getUserNum().equals(userNum))) {
					  userList.add(users);
				  }
			  }
		  }
	  }
	  
	  System.out.println(userList.size());

	  return userList;
  }
  
  //ユーザー情報をアップデート
  @PutMapping("/userDetail/{userNum}")
  public void updateUserDetail(@PathVariable String userNum, String downloadURL) {
	  System.out.println("アップデート="+userNum);
	  System.out.println("photo="+downloadURL);
	  
	  User userDetail = userService.findByUserId(userNum);
	  
	  User user = new User();
	  user.setUserId(userDetail.getUserId());
	  user.setDepName(userDetail.getDepName());
	  user.setHireDate(userDetail.getHireDate());
	  user.setUserNum(userNum);
	  user.setProfile(userDetail.getProfile());
	  user.setPhoto(downloadURL);
	  
	  userService.insertUser(user);
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
