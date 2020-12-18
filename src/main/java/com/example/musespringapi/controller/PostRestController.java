package com.example.musespringapi.controller;

import java.util.List;

import com.example.musespringapi.domain.Music;
import com.example.musespringapi.domain.Post;
import com.example.musespringapi.service.PostCardService;
import com.example.musespringapi.service.PostService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
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

    @PostMapping("/form")
    @ResponseStatus(HttpStatus.CREATED)
    public void InsertMusicPost(@RequestBody Post post) {

        postService.insertPost(post);
    }

    @RequestMapping(value = "/music", method = RequestMethod.POST)
    public void InsertMusicPost(@RequestBody Music music) {

        postService.insertMusic(music);

    }

    @GetMapping("/getPostId")
    public Integer getPostId(String userNum) {
        List<Post> postIdList = postService.findByUserNum(userNum);
        Integer firstId = postIdList.get(0).getPostId();
        Integer maxId = 1;
        for (Post postId : postIdList) {
            if (firstId < postId.getPostId()) {
                maxId = postId.getPostId();
            }
        }
        System.out.println(maxId);
        return maxId;
    }

}
