package com.example.musespringapi.response;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostResponce {

    private List<Integer> postIdAllList;
}
