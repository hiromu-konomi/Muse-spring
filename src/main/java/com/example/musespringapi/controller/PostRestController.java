package com.example.musespringapi.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.example.musespringapi.domain.Check;
import com.example.musespringapi.domain.CheckMusic;
import com.example.musespringapi.domain.Like;
import com.example.musespringapi.domain.Music;
import com.example.musespringapi.domain.Post;

import com.example.musespringapi.domain.ShowReview;
import com.example.musespringapi.domain.User;
import com.example.musespringapi.response.CheckMusicResponse;
import com.example.musespringapi.response.PostResponce;
import com.example.musespringapi.service.CheckService;
import com.example.musespringapi.service.LikeService;
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

    private final LikeService likeService;

    private final CheckService checkService;

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
        System.out.println("firstId ="+ firstId);
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

        //自分の投稿取得
        List<Post> postIdList =postService.getPostIdFromFollowingUser(userNum);
        for(Post post : postIdList ) {
            postIdAllList.add(post);
        }

        for (Post post : postIdAllList) {
            ShowReview showReview = new ShowReview();
            //PostIdで音楽を取ってくる
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

        return new ResponseEntity<>(postResponce, HttpStatus.OK);

    }

    @GetMapping("/getLikePosts")
    public ResponseEntity<PostResponce> getLikePosts(String userNum) {
        System.out.println("ユーザーID＝"+ userNum);
        List<ShowReview> reviewList = new ArrayList<>();

        List<Post> postList = new ArrayList<Post>();
        //いいねした投稿のPostIdを取得
        List<Like> likeList = postService.getLikePosts(userNum);

        if(likeList == null) {
            return null;
        } else {
            for(Like like : likeList ) {
                Post post = postService.getPostByPostId(like.getPostId());
                postList.add(post);
            }
            for (Post post : postList) {
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
                Integer likeCount = likeService.likeList(post.getPostId());
                showReview.setLikeCount(likeCount);

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

            return new ResponseEntity<>(postResponce, HttpStatus.OK);
        }
    }

    @GetMapping("/getCheckSongs")
    public ResponseEntity<CheckMusicResponse> getCheckSongs(String userNum) {
        System.out.println("ユーザーID＝"+ userNum);

        List<CheckMusic> checkMusicList = new ArrayList<CheckMusic>();
        List<Music> musicList = new ArrayList<Music>();
        //チェックしたMusicIdを取得
        List<Check> checkList = checkService.getCheckSongsByUserNum(userNum);

        if(checkList == null) {
            return null;
        } else {
            for(Check check : checkList ) {
                Music music = checkService.getMusicsByMusicId(check.getMusicId());
                musicList.add(music);
            }

            for (Music music : musicList) {

                CheckMusic checkMusic = new CheckMusic();

                checkMusic.setArtistName(music.getArtistName());
                checkMusic.setMusicImage(music.getImage());
                checkMusic.setMusicName(music.getMusicName());
                checkMusic.setMusicId(music.getMusicId());
                checkMusic.setPostId(music.getPostId());

                Check check = checkService.checkList(userNum, music.getMusicId());
                if (check == null) {
                    checkMusic.setCheckStatus(false);
                } else {
                    checkMusic.setCheckStatus(true);
                }
                // musicIdからチェックされた総数を数える
                Integer countCheck = checkService.checkCount(music.getMusicId());
                checkMusic.setCheckCount(countCheck);

                checkMusicList.add(checkMusic);
            }

//            reviewList.sort(Comparator.comparing(ShowReview::getPostId).reversed());
            checkMusicList.sort(Comparator.comparing(CheckMusic::getPostId).reversed());

            CheckMusicResponse checkResponce = CheckMusicResponse.builder().checkAllList(checkMusicList).build();

            return new ResponseEntity<>(checkResponce, HttpStatus.OK);
        }
    }
}
