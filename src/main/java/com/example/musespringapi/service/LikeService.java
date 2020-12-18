package com.example.musespringapi.service;

import java.util.List;

import com.example.musespringapi.domain.Like;
import com.example.musespringapi.domain.Post;
import com.example.musespringapi.repository.LikeRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;

    public void insertPostId(Like like) {
        likeRepository.save(like);
    }

    public void deleteLike(Integer postId) {
        likeRepository.deleteLike(postId);
    }

    public Integer likeList(Integer postId) {
        return likeRepository.likeList(postId);
    }

    public Like userNumAndPostId(String userNum, Integer postId) {
        return likeRepository.userNumAndPostId(userNum, postId);
    }
}
