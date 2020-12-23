package com.example.musespringapi.repository;


import java.util.List;

import javax.transaction.Transactional;

import com.example.musespringapi.domain.Like;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Integer> {
	
	List<Like> findByUserNum(String userNum);
	

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM `likes` WHERE `post_id` = ?1 AND `user_num` = ?2", nativeQuery = true)
    void deleteLike(Integer postId, String userNum);

    @Query(value = "SELECT COUNT(`post_id`) FROM `likes` WHERE `post_id` = ?1", nativeQuery = true)
    Integer likeList(Integer postId);

    @Query(value = "SELECT * FROM `likes` WHERE `user_num` = ?1 AND `post_id` = ?2", nativeQuery = true)
    Like userNumAndPostId(String userNum, Integer postId);
}
