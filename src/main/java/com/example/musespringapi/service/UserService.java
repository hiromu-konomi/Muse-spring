package com.example.musespringapi.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.example.musespringapi.domain.User;
import com.example.musespringapi.repository.UserRepository;



@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public void insertUserId(String userId) {
		User user = new User();
		user.setUserId(userId);
		userRepository.save(user);
	}
}
