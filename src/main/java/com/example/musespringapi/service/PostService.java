package com.example.musespringapi.service;

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

    public void insertPost(String postText, String artistName, String musicName, String userId)  {

        Post post = new Post();
        Music music = new Music();
        Integer postUserId = Integer.valueOf(userId);

        post.setPostText(postText);
        post.setUserId(postUserId);
        postRepository.save(post);

        music.setArtistName(artistName);
        music.setMusicName(musicName);
        musicRepository.save(music);

    }

}
