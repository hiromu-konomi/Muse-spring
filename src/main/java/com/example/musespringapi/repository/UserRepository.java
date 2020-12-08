package com.example.musespringapi.repository;

import com.example.musespringapi.domain.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    
	List<User> findByUserNum(String userNum);
	
	
	

}
