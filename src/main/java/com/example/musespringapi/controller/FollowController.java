package com.example.musespringapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.musespringapi.domain.Relation;
import com.example.musespringapi.domain.User;
import com.example.musespringapi.response.followUserResponse;
import com.example.musespringapi.service.RelationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FollowController {
	
	private final RelationService relationService;
	
	
	@PostMapping("/follow")
	@ResponseStatus(HttpStatus.CREATED)
	public void insertFollowUser(@RequestBody Relation relation) {
		System.out.println(relation.getFollowerUserNum());
		System.out.println(relation.getFollowingUserNum());
		
	
		
			relationService.insertFollowUser(relation);
		
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
}
