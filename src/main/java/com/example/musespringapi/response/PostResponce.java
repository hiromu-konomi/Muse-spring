package com.example.musespringapi.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostResponce {

    List<Integer> postIdAllList = new ArrayList<>();
}
