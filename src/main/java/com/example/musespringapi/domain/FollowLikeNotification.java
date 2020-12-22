package com.example.musespringapi.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FollowLikeNotification {

        //** followNotiid */
        private Integer notiId;

        //** フォローしたuserNum */
        private String userNum;

        /** フォローしたuserName */
        private String userName;

}
