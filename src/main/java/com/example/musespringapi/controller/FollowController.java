package com.example.musespringapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.musespringapi.domain.FollowNotification;
import com.example.musespringapi.domain.Relation;
import com.example.musespringapi.domain.User;
import com.example.musespringapi.service.NotificationService;
import com.example.musespringapi.service.RelationService;
import com.example.musespringapi.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FollowController {

	private final RelationService relationService;
	private final NotificationService notificationService;
	private final UserService userService;
  
	@PostMapping("/follow")
	@ResponseStatus(HttpStatus.CREATED)
	public void insertFollowUser(@RequestBody Relation relation) {
		// System.out.println(relation.getFollowerUserNum());
		// System.out.println(relation.getFollowingUserNum());
		FollowNotification followNotification = new FollowNotification();

		followNotification.setFollowTransfer(relation.getFollowingUserNum());
		followNotification.setFollowReceiver(relation.getFollowerUserNum());

		relationService.insertFollowUser(relation);

		notificationService.insertFollowNotification(followNotification);

	}

//	@GetMapping("/unfollow/{followingUserNum}")
//	public List<Relation> getFollowUser(@PathVariable("followingUserNum") String followingUserNum){
//		return relationService.getFollowerUserNum(followingUserNum);
//	}

	@DeleteMapping("/deletefollowUser")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteFollowUser(String followingUserNum, String followerUserNum) {
		System.out.println(followingUserNum);
		System.out.println(followerUserNum);
//		System.out.println(relation.getFollowingUserNum());
//		System.out.println(relation.getFollowerUserNum());
		relationService.deleteFollowUser(followingUserNum, followerUserNum);
	}
	
	
	@GetMapping("/followingId")
	public List<User> findByFollowingUserNum(String followingUserNum){
	
		List<Relation> relationList =  relationService.findByFollowingUserNum(followingUserNum);
		List<User> userList = new ArrayList<User>();
		if(relationList == null) {
			return null;
		} else {
			for(Relation relation : relationList) {
				System.out.println(relation.getFollowerUserNum());
				User user = relationService.findByUserNum(relation.getFollowerUserNum());
				userList.add(user);
			}
		}
		System.out.println(userList);
		
		return userList;
	}
	
//	@GetMapping("/followUser")
//	public List<User> findByUserNum(String userNum){
//		System.out.println("ユーザーId ="+userNum);
//		System.out.println(relationService.findByUserNum(userNum));
//		System.out.println(relationService.findByUserNum(userNum).get(0).getUserNum());
//		return relationService.findByUserNum(userNum);
//	}
//	
	@GetMapping("/followerId")
	public List<User> findByFollowerUserNum(String followerUserNum){
		List<Relation> relationList = relationService.findByFollowerUserNum(followerUserNum);
		List<User> userList = new ArrayList<User>();
		
		if(relationList == null) {
			return null;
		} else {
			for(Relation relation : relationList) {
				System.out.println(relation.getFollowingUserNum());
				User user = relationService.findByUserNum(relation.getFollowingUserNum());
				userList.add(user);
			}	
		}
		
		System.out.println(userList);
		return userList;
	}

	// フォローしているユーザーを名前で曖昧検索する際に GET されるメソッド
	@GetMapping("/searchFollowing")
	public List<User> getFollowingUsersBySearch(String followingUserNum, String searchWord) {

		List<Relation> relationList =  relationService.findByFollowingUserNum(followingUserNum);
		List<User> userList = new ArrayList<>();

		if(relationList == null) {
			return null;
		} else {
			for(Relation relation : relationList) {
				String userNum = relation.getFollowerUserNum();
				User user = userService.findByUserNumAndSearchWord(userNum, searchWord);
				if (Objects.isNull(user)) {
					continue;
				}
				userList.add(user);
			}
		}

		return userList;
	}
}
