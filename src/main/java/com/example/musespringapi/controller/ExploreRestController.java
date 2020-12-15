package com.example.musespringapi.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.musespringapi.domain.Explore;
import com.example.musespringapi.domain.Music;
import com.example.musespringapi.domain.Post;
import com.example.musespringapi.domain.User;
import com.example.musespringapi.response.MusicForExploreResponse;
import com.example.musespringapi.service.ExploreService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ExploreRestController {

    private final ExploreService exploreService;

    @RequestMapping(value = "/searchPost", method = RequestMethod.GET)
    public ResponseEntity<MusicForExploreResponse> SelectPost(String searchPost){

        List<Music> musicList = exploreService.postIdFindByMusicName(searchPost);
        List<Explore> exploreList = new ArrayList<>();

        for(Music music : musicList){

            Explore explore = new Explore();

            explore.setArtistName(music.getArtistName());
            explore.setMusicName(music.getMusicName());
            explore.setMusicImage(music.getImage());

            Post post = exploreService.userNumPostTextFindByPostId(music.getPostId());

            explore.setPostText(post.getPostText());

            Integer userIntegerNum = Integer.valueOf(post.getUserNum());
            User user = exploreService.userNameFindByUserNum(userIntegerNum);

            explore.setUserName(user.getUserName());

            exploreList.add(explore);

            // System.out.println("test");

        }

        MusicForExploreResponse musicForExploreResponse = MusicForExploreResponse.builder().exploreList(exploreList).build();
        // System.out.println(musicForExploreResponse.getExploreList().get(0).getArtistName());
        return new ResponseEntity<>(musicForExploreResponse, HttpStatus.OK);

    }

}
