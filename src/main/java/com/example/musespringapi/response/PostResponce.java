package com.example.musespringapi.response;

import java.util.ArrayList;
import java.util.List;

import com.example.musespringapi.domain.ShowReview;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostResponce {

    private List<ShowReview> reviewAllList;
}
