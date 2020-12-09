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
public class PostService {

    private final PostRepository postRepository;
    private final MusicRepository musicRepository;

    public void insertMusic(Music music) {
        System.out.println(music.getGenreName());
        musicRepository.save(music);
    }

    public void insertPost(Post post) {
        postRepository.save(post);
    }

    public List<Post> findByPostId(String userNum) {
        return postRepository.findByUserNum(userNum);
    }

}
