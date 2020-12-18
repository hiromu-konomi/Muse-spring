package com.example.musespringapi.response;

import java.util.List;

import com.example.musespringapi.domain.FollowLikeNotification;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FollowLikeNotificationResponse {

    //** フォローしたuserNum */
    private List<FollowLikeNotification> followLikeNotificationList;

}
