package com.example.musespringapi.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.example.musespringapi.domain.Music;
import com.example.musespringapi.response.MusicResponce;
import com.example.musespringapi.response.PostResponce;
import com.example.musespringapi.service.PostCardService;
import com.example.musespringapi.service.PostService;
import com.example.musespringapi.service.RelationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javassist.expr.NewArray;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HomeRestController {

    private final PostCardService postCardService;
    private final RelationService relationService;
    private final PostService postService;

    @GetMapping("/getMusicInfo")
    public MusicResponce getMusicInfo(Integer postId) {
        Music music = postCardService.findByPostId(postId);
        MusicResponce musicResponce = new MusicResponce();
        musicResponce.setArtistName(music.getArtistName());
        musicResponce.setMusicName(music.getMusicName());
        musicResponce.setImage(music.getImage());
        System.out.println(musicResponce.getImage());
        return musicResponce;
    }

    //
    @GetMapping("/getFollowingUser")
    public ResponseEntity<PostResponce> getFollowingUserNum(String userNum) {
        List<String> followingUserList = relationService.getFollowingUserNum(userNum);
        List<Integer> postIdAllList = new ArrayList<>();
        for (String followingUserNum : followingUserList) {
            List<Integer> postIdList = postService.getPostIdFromFollowingUser(followingUserNum);
            for (Integer postId : postIdList) {
                postIdAllList.add(postId);
            }
        }
        postIdAllList.sort(Comparator.reverseOrder());
        PostResponce postResponce = PostResponce.builder().postIdAllList(postIdAllList).build();

        return new ResponseEntity<>(postResponce, HttpStatus.OK);
    }

}
