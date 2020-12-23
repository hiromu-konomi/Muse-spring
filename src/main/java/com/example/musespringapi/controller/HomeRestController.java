package com.example.musespringapi.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.example.musespringapi.domain.Check;
import com.example.musespringapi.domain.Like;
import com.example.musespringapi.domain.LikeNotification;
import com.example.musespringapi.domain.Music;
import com.example.musespringapi.domain.Post;
import com.example.musespringapi.domain.ShowReview;
import com.example.musespringapi.domain.User;
import com.example.musespringapi.response.PostResponce;
import com.example.musespringapi.service.CheckService;
import com.example.musespringapi.service.LikeService;
import com.example.musespringapi.service.NotificationService;
import com.example.musespringapi.service.PostCardService;
import com.example.musespringapi.service.PostService;
import com.example.musespringapi.service.RelationService;
import com.example.musespringapi.service.UserService;
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
    private final NotificationService notificationService;
    private final PostService postService;
    private final UserService userService;
    private final LikeService likeService;
    private final CheckService checkService;

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
            showReview.setUserNum(post.getUserNum());

            Like like = likeService.userNumAndPostId(userNum, post.getPostId());
            if (like == null) {
                showReview.setLikeStatus(false);
            } else {
                showReview.setLikeStatus(true);
            }
            Integer likeList = likeService.likeList(post.getPostId());
            showReview.setLikeCount(likeList);
            // postIdからmusicIdの取得
            Integer musicId = checkService.getMusicId(post.getPostId());
            Check check = checkService.checkList(userNum, musicId);
            if (check == null) {
                showReview.setCheckStatus(false);
            } else {
                showReview.setCheckStatus(true);
            }
            // musicIdからチェックされた総数を数える
            Integer countCheck = checkService.checkCount(musicId);
            showReview.setCheckCount(countCheck);
            reviewList.add(showReview);
        }

        reviewList.sort(Comparator.comparing(ShowReview::getPostId).reversed());

        PostResponce postResponce = PostResponce.builder().reviewAllList(reviewList).build();
        System.out.println(postResponce.getReviewAllList().toString());
        return new ResponseEntity<>(postResponce, HttpStatus.OK);

    }

    @GetMapping("/like")
    public Integer insertLike(Integer postId, String userNum) {

        Like like = new Like();
        LikeNotification likeNotification = new LikeNotification();

        Post post = notificationService.getPostByPostId(postId);

        likeNotification.setLikeReceiver(post.getUserNum());
        likeNotification.setLikeTransfer(userNum);

        notificationService.insertLikeNotification(likeNotification);

        like.setPostId(postId);
        like.setUserNum(userNum);

        likeService.insertPostId(like);

        Integer likeList = likeService.likeList(postId);
        return likeList;
    }

    @GetMapping("/notLike")
    public void deleteLike(Integer postId, String userNum) {
        likeService.deleteLike(postId, userNum);
    }

    @GetMapping("/check")
    public Integer insertChecks(Integer postId, String userNum) {

        Integer musicId = checkService.getMusicId(postId);
        Check check = new Check();
        check.setMusicId(musicId);
        check.setUserNum(userNum);

        checkService.insertChecks(check);

        Integer checkCount = checkService.checkCount(musicId);
        return checkCount;
    }

    @GetMapping("/notCheck")
    public void deleteChecks(Integer postId, String userNum) {
        Integer getMusicId = checkService.getMusicId(postId);
        checkService.deleteChecks(getMusicId, userNum);
    }

    @GetMapping("/deleteInfo")
    public void deleteInfo(Integer postId, String userNum) {
        postService.deleteMusic(postId);
        postService.deletePost(postId);
        likeService.deleteLike(postId, userNum);
        Integer getMusicId = checkService.getMusicId(postId);
        checkService.deleteChecks(getMusicId, userNum);
        System.out.println(postId);
    }

}
