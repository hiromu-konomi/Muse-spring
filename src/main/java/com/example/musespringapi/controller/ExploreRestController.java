package com.example.musespringapi.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.musespringapi.domain.Group;
import com.example.musespringapi.domain.Music;
import com.example.musespringapi.domain.Post;
import com.example.musespringapi.domain.ShowReview;
import com.example.musespringapi.domain.User;
import com.example.musespringapi.response.MusicForExploreResponse;
import com.example.musespringapi.service.ExploreService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ExploreRestController {

    private final ExploreService exploreService;

    @RequestMapping(value = "/searchPost", method = RequestMethod.GET)
    public ResponseEntity<MusicForExploreResponse> SelectPost(String searchPost) {

        List<Music> musicList = exploreService.postIdFindByMusicName(searchPost);
        List<ShowReview> exploreList = new ArrayList<>();

        for (Music music : musicList) {

            ShowReview showReview = new ShowReview();

            showReview.setArtistName(music.getArtistName());
            showReview.setMusicName(music.getMusicName());
            showReview.setMusicImage(music.getImage());

            System.out.println("showReview.artistName = " + showReview.getArtistName());

            Post post = exploreService.userNumPostTextFindByPostId(music.getPostId());

            showReview.setPostText(post.getPostText());

            User user = exploreService.userNameFindByUserNum(post.getUserNum());

            showReview.setUserName(user.getUserName());
            showReview.setUserNum(user.getUserNum());

            exploreList.add(showReview);

            // System.out.println("test");

        }

        MusicForExploreResponse musicForExploreResponse = MusicForExploreResponse.builder().exploreList(exploreList)
                .build();
        // System.out.println(musicForExploreResponse.getExploreList().get(0).getArtistName());
        return new ResponseEntity<>(musicForExploreResponse, HttpStatus.OK);

    }

    @GetMapping("/searchUsers")
    public List<User> exploreUserFromUserName(String searchUser){

        List<User> userListFromExplore = exploreService.findUserByUserNameStartsWith(searchUser);
        return userListFromExplore;
    }

    @GetMapping("/searchGroups")
    public List<Group> exploreGroupFromGroupName(String searchGroup){

        List<Group> exploreGroupFromGroupName = exploreService.findGroupsByGroupNameStartsWith(searchGroup);
        return exploreGroupFromGroupName;
    }

}
