package com.example.musespringapi.controller;

import com.example.musespringapi.domain.Music;
import com.example.musespringapi.domain.Post;
import com.example.musespringapi.service.PostService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PostRestController {

    private final PostService postService;


    // @RequestMapping(value = "/review", method = RequestMethod.POST)
    // public void InsertMusicPost(@RequestBody String artistName, String musicName,
    // String userId, String postText) {
    // System.out.println(artistName);
    // System.out.println(musicName);
    // System.out.println(userId);
    // System.out.println(postText);

    // postService.insertPost(postText, artistName, musicName, userId);

    // }

    @PostMapping("/form")
    @ResponseStatus(HttpStatus.CREATED)
    public void InsertMusicPost(@RequestBody Post post) {

        postService.insertPost(post);
    }

    @RequestMapping(value = "/music", method = RequestMethod.POST)
    public void InsertMusicPost(@RequestBody Music music) {
  
        postService.insertMusic(music);

    }

}
