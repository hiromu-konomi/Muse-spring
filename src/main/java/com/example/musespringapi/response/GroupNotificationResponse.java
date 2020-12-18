package com.example.musespringapi.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GroupNotificationResponse{

    /** フォローした人のuserNum */
    private String userNum;

    /** 通知タイプ */
    private String notificationType;

    /** 招待されたgroupId */
    private Integer groupId;

    /** 招待されたgroupName */
    private String groupName;

}
