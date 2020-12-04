package com.example.musespringapi.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.musespringapi.domain.User;
import com.example.musespringapi.repository.FirebaseRepository;
import com.example.musespringapi.repository.UserRepository;

import java.util.List;

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

	public List<User> findByUserNum(String userNum) {
		return firebaseRepository.findByUserNum(userNum);
	}
}
