package com.example.musespringapi.response;

import java.util.List;

import com.example.musespringapi.domain.Explore;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MusicForExploreResponse {

    private List<Explore> exploreList;

}
