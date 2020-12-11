package com.example.musespringapi.controller;

import java.util.List;

import com.example.musespringapi.domain.Music;
import com.example.musespringapi.domain.Post;
import com.example.musespringapi.response.ReviewResponce;
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

    private final PostCardService postCardService;

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

    @GetMapping("/getPostId")
    public Integer getPostId(String userNum) {
        List<Post> postIdList = postService.findByPostId(userNum);
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

    @GetMapping("/getReview")
    public String getReview(Integer postId) {
        Post post = postCardService.getReview(postId);
        ReviewResponce reviewResponce = new ReviewResponce();
        reviewResponce.setReview(post.getPostText());
        System.out.println(post.getPostText());
        return reviewResponce.getReview();
    }

}
