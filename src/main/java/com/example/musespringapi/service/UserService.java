package com.example.musespringapi.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.musespringapi.domain.User;
import com.example.musespringapi.repository.FirebaseRepository;
import com.example.musespringapi.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	private final FirebaseRepository firebaseRepository;

	public void insertUserId(String userNum) {
		User user = new User();
		user.setUserNum(userNum);
		userRepository.save(user);
	}

	// UserIdの有無を調べる
	public User findByUserId(String userNum) {
		List<User> userList = userRepository.findByUserNum(userNum);
		if (userList.isEmpty()) {
			return null;
		}
		return userList.get(0);
	}

	// ユーザー情報をインサートする
	public void insertUser(User user) {
		userRepository.save(user);
	}

	public List<User> findByUserNum(String userNum) {
		return firebaseRepository.findByUserNum(userNum);
	}

	
	//入社日が同じ日のユーザー検索
	public List<User> findByHireDate(Date hireDate){
		return userRepository.findByHireDate(hireDate);
	};


	// Firebase の ID をもとにユーザー名を検索
	public String userNameFindByUserNum(String userNum) {
		return userRepository.userNameFindByUserNum(userNum);
	}
}
