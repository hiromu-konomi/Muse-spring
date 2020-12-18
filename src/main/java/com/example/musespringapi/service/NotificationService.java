package com.example.musespringapi.service;

import java.util.List;

import com.example.musespringapi.domain.FollowNotification;
import com.example.musespringapi.domain.GroupNotification;
import com.example.musespringapi.domain.NotificationParents;
import com.example.musespringapi.domain.User;
import com.example.musespringapi.repository.FollowNotificationRepository;
import com.example.musespringapi.repository.GroupNotificationRepository;
import com.example.musespringapi.repository.NotificationParentsRepository;
import com.example.musespringapi.repository.UserRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationParentsRepository notificationParentsRepository;
    private final FollowNotificationRepository followNotificationRepository;
    private final GroupNotificationRepository groupNotificationRepository;
    private final UserRepository userRepository;

    //** フォロー、フォロワー時通知を誰が受け取るのかのインポート */
    public void insertFollowNotification(FollowNotification followNotification) {
        followNotificationRepository.save(followNotification);
    }

    /** 誰がどのグループに招待したのかインポート */
    public void insertGroupNotification(GroupNotification groupNotification) {
        groupNotificationRepository.save(groupNotification);
    }

    //** フォロー受け取った人よりフォローした人のfollowNum,typeをselect */
    public List<FollowNotification> getAllByFollowerReceiver(String followReceiver){
        return followNotificationRepository.getFollowNotificationByReceiver(followReceiver);
    }

    public User getUserByFollowReceiver(String followTransfer){
        return userRepository.userNameList(followTransfer);
    }

    public NotificationParents getNotiPareByNotiPareId(Integer notiPareId){
        return notificationParentsRepository.getNotiPareByNotiPareId(notiPareId);
    }

}
