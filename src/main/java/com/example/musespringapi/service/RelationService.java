package com.example.musespringapi.service;

import java.util.List;

import com.example.musespringapi.domain.Relation;
import com.example.musespringapi.domain.User;
import com.example.musespringapi.repository.RelationRepository;
import com.example.musespringapi.repository.UserRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RelationService {

    private final RelationRepository relationRepository;
    private final UserRepository userRepository;


    public List<String> getFollowerUserNum(String userNum) {
        return relationRepository.getFollowerUserNum(userNum);
    }

    //フォロしたらそれぞれにユーザーIDをインサート
    public void insertFollowUser(Relation relation) {
    	
    	relationRepository.save(relation);
    }
    
    public void deleteFollowUser(String followingUserNum, String followerUserNum) {
    	relationRepository.DeleteFollowUser(followingUserNum, followerUserNum);
    }

  //自分がフォローしているユーザーIDを取ってくる
    public List<Relation> findByFollowingUserNum(String followingUserNum){
    	List<Relation> followingUserNumList =  relationRepository.findByFollowingUserNum(followingUserNum);
    	if (followingUserNumList.isEmpty()) {
			return null;
		}
    	return followingUserNumList;
    }
    
    //フォローしてるユーザーの情報を取ってくる
    public User findByUserNum(String userNum){
    	List<User> userList = userRepository.findByUserNum(userNum);
    	
    	if(userList.isEmpty()) {
    		return null;
    	}
    	return userList.get(0);
    }
    
   //フォローされているユーザーを取ってくる
    public List<Relation> findByFollowerUserNum (String followerUserNum){
    	List<Relation> followerUserNumList = relationRepository.findByFollowerUserNum(followerUserNum);
    	
    	if(followerUserNumList.isEmpty()) {
    		return null;
    	}
    	
    	return followerUserNumList;
    }
    
}
