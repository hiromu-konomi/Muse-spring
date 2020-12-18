package com.example.musespringapi.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FollowLikeNotification {

        //** id */
        private Integer followNotiId;

        //** フォローしたuserNum */
        private String userNum;

        /** フォローしたuserName */
        private String userName;

        /** 通知タイプ */
        private String notificationType;

}
