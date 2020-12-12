package com.example.musespringapi.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FollowUserGrpSts {
    private String userNum;
    private String userName;
    private Integer joinStatus;
}
