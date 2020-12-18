package com.example.musespringapi.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.example.musespringapi.domain.Like;
import com.example.musespringapi.domain.Music;
import com.example.musespringapi.domain.Post;
import com.example.musespringapi.domain.ShowReview;
import com.example.musespringapi.domain.User;
import com.example.musespringapi.response.PostResponce;
import com.example.musespringapi.service.LikeService;
import com.example.musespringapi.service.PostCardService;
import com.example.musespringapi.service.PostService;
import com.example.musespringapi.service.RelationService;
import com.example.musespringapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HomeRestController {

    private final PostCardService postCardService;
    private final RelationService relationService;
    private final PostService postService;
    private final UserService userService;
    private final LikeService likeService;

    @GetMapping("/getMusicInfoAndReview")
    public ResponseEntity<PostResponce> getMusicInfo(String userNum) {
        List<String> followingUserList = relationService.getFollowerUserNum(userNum);
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
            Like like = likeService.userNumAndPostId(userNum, post.getPostId());
            if (like == null) {
                showReview.setLikeStatus(false);
            } else {
                showReview.setLikeStatus(true);
            }
            Integer likeList = likeService.likeList(post.getPostId());
            showReview.setLikeCount(likeList);
            reviewList.add(showReview);
        }

        reviewList.sort(Comparator.comparing(ShowReview::getPostId).reversed());

        PostResponce postResponce = PostResponce.builder().reviewAllList(reviewList).build();
        return new ResponseEntity<>(postResponce, HttpStatus.OK);

    }

    @GetMapping("/like")
    public Integer insertLike(Integer postId, String userNum) {

        System.out.println("postId=" + postId);

        Like like = new Like();
        like.setPostId(postId);
        like.setUserNum(userNum);

        likeService.insertPostId(like);

        Integer likeList = likeService.likeList(postId);
        System.out.println(likeList);
        return likeList;
    }

    @GetMapping("/notLike")
    public void deleteLike(Integer postId) {
        likeService.deleteLike(postId);
    }

}
