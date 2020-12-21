package com.example.musespringapi.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.musespringapi.domain.FollowLikeNotification;
import com.example.musespringapi.domain.FollowNotification;
import com.example.musespringapi.domain.Group;
import com.example.musespringapi.domain.GroupNotification;
import com.example.musespringapi.domain.GroupToResponse;
import com.example.musespringapi.domain.LikeNotification;
import com.example.musespringapi.domain.NotificationParents;
import com.example.musespringapi.domain.User;
import com.example.musespringapi.response.FollowLikeNotificationResponse;
import com.example.musespringapi.response.GroupToResponseResponse;
import com.example.musespringapi.service.NotificationService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    //** フォロー通知の必要情報を取ってくる作業 */
    @RequestMapping(value = "/followNotification" , method = RequestMethod.GET)
    public ResponseEntity<FollowLikeNotificationResponse> getFollowNotification(String receiverUserNum){

        List<FollowNotification> followNotificationList = notificationService.getAllByFollowerReceiver(receiverUserNum);
        List<FollowLikeNotification> followLikeNotificationList = new ArrayList<>();

        for(FollowNotification folNoti : followNotificationList){

            FollowLikeNotification followLikeNotification = new FollowLikeNotification();

            followLikeNotification.setNotiId(folNoti.getFollowNotificationId());
            followLikeNotification.setUserNum(folNoti.getFollowTransfer());

            User user = notificationService.getUserByTransfer(folNoti.getFollowTransfer());
            followLikeNotification.setUserName(user.getUserName());

            NotificationParents notiPare = notificationService.getNotiPareByNotiPareId(folNoti.getNotificationParentsId());
            followLikeNotification.setNotificationType(notiPare.getNotificationType());

            followLikeNotificationList.add(followLikeNotification);

        }

        FollowLikeNotificationResponse followLikeNotificationResponse = FollowLikeNotificationResponse.builder()
            .followLikeNotificationList(followLikeNotificationList).build();

        return new ResponseEntity<>(followLikeNotificationResponse, HttpStatus.OK);

    }

    /** いいね通知に必要な情報を持ってくる処理 */
    @RequestMapping(value = "/likeNotification", method = RequestMethod.GET)
    public ResponseEntity<FollowLikeNotificationResponse> getLikeNotification(String receiverUserNum){

        List<LikeNotification> likeNotificationList = notificationService.getLikeNotificationListByReceiver(receiverUserNum);
        List<FollowLikeNotification> followLikeNotificationList = new ArrayList<>();

        for(LikeNotification likeNoti : likeNotificationList){

            FollowLikeNotification followLikeNotification = new FollowLikeNotification();

            followLikeNotification.setNotiId(likeNoti.getLikeNotificationId());
            followLikeNotification.setUserNum(likeNoti.getLikeTransfer());

            User user = notificationService.getUserByTransfer(likeNoti.getLikeTransfer());
            followLikeNotification.setUserName(user.getUserName());

            NotificationParents notiPare = notificationService.getNotiPareByNotiPareId(likeNoti.getNotificationParentsId());
            followLikeNotification.setNotificationType(notiPare.getNotificationType());

            followLikeNotificationList.add(followLikeNotification);

        }

        FollowLikeNotificationResponse followLikeNotificationRes = FollowLikeNotificationResponse.builder()
            .followLikeNotificationList(followLikeNotificationList).build();

        return new ResponseEntity<>(followLikeNotificationRes, HttpStatus.OK);

    }

    /** グループ招待通知に必要な処理 */
    @RequestMapping(value = "/groupNotification", method = RequestMethod.GET)
    public ResponseEntity<GroupToResponseResponse> getGroupNotification(String receiverUserNum){

        List<GroupNotification> groupNotificationList = notificationService.getGroupNotificationListByReceiver(receiverUserNum);
        List<GroupToResponse> groupToResponseList = new ArrayList<GroupToResponse>();

        for(GroupNotification grpNoti : groupNotificationList){

            GroupToResponse groupToResponse = new GroupToResponse();

            groupToResponse.setGroupNotificationId(grpNoti.getGroupNotificationId());

            User user = notificationService.getUserByTransfer(grpNoti.getGroupTransfer());

            groupToResponse.setTransferUserName(user.getUserName());

            Group group = notificationService.getGroupByGroupId(grpNoti.getGroupId());

            groupToResponse.setGroupId(grpNoti.getGroupId());
            groupToResponse.setGroupName(group.getGroupName());

            groupToResponseList.add(groupToResponse);

        }

        GroupToResponseResponse groupToResRes = GroupToResponseResponse.builder()
        .groupToResponseList(groupToResponseList).build();

        return new ResponseEntity<>(groupToResRes, HttpStatus.OK);

    }

}
