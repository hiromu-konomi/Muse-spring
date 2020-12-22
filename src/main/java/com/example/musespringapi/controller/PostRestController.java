package com.example.musespringapi.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.example.musespringapi.domain.Music;
import com.example.musespringapi.domain.Post;

import com.example.musespringapi.domain.ShowReview;
import com.example.musespringapi.domain.User;
import com.example.musespringapi.response.PostResponce;

import com.example.musespringapi.service.PostCardService;
import com.example.musespringapi.service.PostService;
import com.example.musespringapi.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private final UserService userService;

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
            if (firstId <= postId.getPostId()) {
                maxId = postId.getPostId();
            }
        }
        System.out.println(maxId);
        return maxId;
    }

    @GetMapping("/getMyPosts")
    public ResponseEntity<PostResponce> getMusicInfo(String userNum) {
        List<ShowReview> reviewList = new ArrayList<>();
        List<Post> postIdAllList = new ArrayList<>();
        List<Post> postIdList = postService.getPostIdFromFollowingUser(userNum);
        for (Post post : postIdList) {
            postIdAllList.add(post);
        }

        for (Post post : postIdAllList) {
            ShowReview showReview = new ShowReview();
            Music music = postCardService.findByPostId(post.getPostId());
            showReview.setArtistName(music.getArtistName());
            showReview.setMusicImage(music.getImage());
            showReview.setMusicName(music.getMusicName());
            User user = userService.userName(post.getUserNum());
            showReview.setUserName(user.getUserName());
            showReview.setPostText(post.getPostText());
            showReview.setPostId(post.getPostId());
            showReview.setUserNum(post.getUserNum());

            reviewList.add(showReview);
        }

        reviewList.sort(Comparator.comparing(ShowReview::getPostId).reversed());

        PostResponce postResponce = PostResponce.builder().reviewAllList(reviewList).build();
        System.out.println("投稿数＝" + postResponce.getReviewAllList().size());
        return new ResponseEntity<>(postResponce, HttpStatus.OK);

    }

}
