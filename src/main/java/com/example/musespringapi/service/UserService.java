package com.example.musespringapi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.musespringapi.domain.User;
import com.example.musespringapi.repository.UserRepository;



@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	//userIdをinsertする処理
	public void insertUserId(String userId) {
		User user = new User();
		user.setUserId(userId);
		userRepository.save(user);
	}
	
	//UserIdの有無を調べる
	public User findByUserId(String userId){
		List<User> userList = userRepository.findByUserId(userId);
		if(userList.isEmpty()) {
			return null;
		}
		return userList.get(0);
	}
	
	//ユーザー情報をインサートする
	public void insertUser(User user) {
		userRepository.save(user);
	}
}
