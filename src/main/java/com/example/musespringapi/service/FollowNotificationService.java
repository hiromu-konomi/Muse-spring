package com.example.musespringapi.service;

import com.example.musespringapi.domain.FollowNotification;
import com.example.musespringapi.repository.FollowNotificationRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FollowNotificationService {

    private final FollowNotificationRepository followNotificationRepository;

    //** フォロー、フォロワー時通知を誰が受け取るのかの処理 */
    public void insertFollowNotification(FollowNotification followNotification) {
        followNotificationRepository.save(followNotification);
    }

}
