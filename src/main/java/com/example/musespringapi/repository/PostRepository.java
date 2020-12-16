package com.example.musespringapi.repository;

import java.util.List;

import com.example.musespringapi.domain.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    // userNumから検索する処理
    List<Post> findByUserNum(String userNum);

    // postIdから検索する処理
    @Query(value = "SELECT * FROM `posts` WHERE `post_id` = ?1 ORDER BY `post_id` DESC", nativeQuery = true)
    List<Post> findByPostId(Integer postId);

    // フォローしてるpostIDを取ってくる処理
    @Query(value = "SELECT * FROM `posts` WHERE `user_num` = ?1 ORDER BY `post_id` DESC", nativeQuery = true)
    List<Post> getPostIdFromFollowingUser(String followingUser);

}
