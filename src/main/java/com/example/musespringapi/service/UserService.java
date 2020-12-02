package com.example.musespringapi.service;

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
	
	public void insertUserId(User userId) {
		userRepository.save(userId);
	}
}
