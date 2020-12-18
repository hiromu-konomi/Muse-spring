package com.example.musespringapi.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.musespringapi.domain.FollowLikeNotification;
import com.example.musespringapi.domain.FollowNotification;
import com.example.musespringapi.domain.NotificationParents;
import com.example.musespringapi.domain.User;
import com.example.musespringapi.response.FollowLikeNotificationResponse;
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
        System.out.println(followNotificationList.size());
        List<FollowLikeNotification> followLikeNotificationList = new ArrayList<>();

        for(FollowNotification folNoti : followNotificationList){

            FollowLikeNotification followLikeNotification = new FollowLikeNotification();

            followLikeNotification.setFollowNotiId(folNoti.getFollowNotificationId());
            followLikeNotification.setUserNum(folNoti.getFollowTransfer());

            User user = notificationService.getUserByFollowReceiver(folNoti.getFollowTransfer());
            followLikeNotification.setUserName(user.getUserName());

            NotificationParents notiPare = notificationService.getNotiPareByNotiPareId(folNoti.getNotificationParentsId());
            followLikeNotification.setNotificationType(notiPare.getNotificationType());

            System.out.println(followLikeNotification.getUserName());

            followLikeNotificationList.add(followLikeNotification);

        }

        FollowLikeNotificationResponse followLikeNotificationResponse = FollowLikeNotificationResponse.builder()
            .followLikeNotificationList(followLikeNotificationList).build();

        return new ResponseEntity<>(followLikeNotificationResponse, HttpStatus.OK);

    }

}
