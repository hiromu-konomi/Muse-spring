package com.example.musespringapi.service;

import java.util.List;

import com.example.musespringapi.domain.Music;
import com.example.musespringapi.domain.Post;
import com.example.musespringapi.repository.MusicRepository;
import com.example.musespringapi.repository.PostRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostCardService {
    private final MusicRepository musicRepository;
    private final PostRepository postRepository;

    public Music findByPostId(Integer postId) {
        return musicRepository.getMusicInfo(postId);
    }

    public List<Post> getReview(Integer postId) {
        return postRepository.findByPostId(postId);
    }

}
