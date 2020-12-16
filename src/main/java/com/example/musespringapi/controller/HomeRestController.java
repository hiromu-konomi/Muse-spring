package com.example.musespringapi.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;

import com.example.musespringapi.domain.Music;
import com.example.musespringapi.domain.Post;
import com.example.musespringapi.domain.ShowReview;
import com.example.musespringapi.domain.User;
import com.example.musespringapi.response.PostResponce;
import com.example.musespringapi.service.PostCardService;
import com.example.musespringapi.service.PostService;
import com.example.musespringapi.service.RelationService;
import com.example.musespringapi.service.UserService;

import org.hibernate.engine.internal.Collections;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HomeRestController {

    private final PostCardService postCardService;
    private final RelationService relationService;
    private final PostService postService;
    private final UserService userService;

    @GetMapping("/getMusicInfoAndReview")
    public ResponseEntity<PostResponce> getMusicInfo(String userNum) {
        List<String> followingUserList = relationService.getFollowingUserNum(userNum);
        followingUserList.add(userNum);
        List<ShowReview> reviewList = new ArrayList<>();
        List<Post> postIdAllList = new ArrayList<>();
        for (String followingUserNum : followingUserList) {
            List<Post> postIdList = postService.getPostIdFromFollowingUser(followingUserNum);
            for (Post post : postIdList) {
                postIdAllList.add(post);
            }
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
            reviewList.add(showReview);
        }

        reviewList.sort(Comparator.comparing(ShowReview::getPostId).reversed());

        PostResponce postResponce = PostResponce.builder().reviewAllList(reviewList).build();
        System.out.println(postResponce.getReviewAllList().size());
        return new ResponseEntity<>(postResponce, HttpStatus.OK);

    }

}
