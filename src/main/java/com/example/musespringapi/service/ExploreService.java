package com.example.musespringapi.service;

import java.util.List;

import com.example.musespringapi.domain.Music;
import com.example.musespringapi.domain.Post;
import com.example.musespringapi.domain.User;
import com.example.musespringapi.repository.MusicRepository;
import com.example.musespringapi.repository.PostRepository;
import com.example.musespringapi.repository.UserRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExploreService {

    private final MusicRepository musicRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public List<Music> postIdFindByMusicName(String searchPost){

        return musicRepository.findByArtistNameStartsWith(searchPost);
    }

    public Post userNumPostTextFindByPostId(Integer postId){
        return postRepository.getPostTextUserIdByPostId(postId);
    }

    public User userNameFindByUserNum(String userNum){
        return userRepository.userNameList(userNum);
    }

}
