package com.example.musespringapi.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.musespringapi.domain.Music;
import com.example.musespringapi.service.ExploreService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ExploreRestController {

    private final ExploreService exploreService;

    @RequestMapping(value = "/searchPost", method = RequestMethod.GET)
    public List<Integer> SelectPost(String searchPost){

        List<Music> postList = exploreService.postIdFindByMusicName(searchPost);
        List<Integer> postIdList = new ArrayList<Integer>();

        for(Music music : postList){
            postIdList.add(music.getPostId());
        }

        return postIdList;

    }

}
