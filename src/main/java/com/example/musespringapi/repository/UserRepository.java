package com.example.musespringapi.repository;

import com.example.musespringapi.domain.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByUserNum(String userNum);

	// Firebase の ID をもとにユーザー名を検索
	@Query(value = "SELECT `user_name` FROM `users` WHERE `user_num` = ?1", nativeQuery = true)
	String userNameFindByUserNum(String userNum);

}
