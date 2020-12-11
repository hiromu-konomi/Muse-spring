package com.example.musespringapi.repository;

import java.util.List;

import com.example.musespringapi.domain.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findByUserNum(String userNum);

    Post findByPostId(Integer postId);

}
