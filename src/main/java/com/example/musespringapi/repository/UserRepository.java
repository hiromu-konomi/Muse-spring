package com.example.musespringapi.repository;

import com.example.musespringapi.domain.User;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByUserNum(String userNum);

	List<User> findByHireDate(Date hireDate);

	// userNumをもとにユーザー名を検索
	@Query(value = "SELECT `user_name` FROM `users` WHERE `user_num` = ?1", nativeQuery = true)
	String userNameFindByUserNum(String userNum);


	@Query(value = "SELECT * FROM `users` WHERE `user_num` = ?1", nativeQuery = true)
	User userNameList(String userNum);

	//userIdよりuserNameを取得
	@Query(value = "SELECT * FROM `users` WHERE `user_num` = ?1", nativeQuery = true)
	User getUserNameByUserId(Integer userId);

	// userNumとuserNameの曖昧検索をもとにユーザーを取得
	@Query(value = "SELECT * FROM `users` WHERE `user_num` = ?1 AND `user_name` LIKE %?2%", nativeQuery = true)
	User findByUserNumAndSearchWord(String userNum, String searchWord);

}
