package com.example.musespringapi.service;

import com.example.musespringapi.domain.NotificationParents;
import com.example.musespringapi.repository.NotificationParentsRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationParentsService {

    private final NotificationParentsRepository notificationParentsRepository;

    public void insertNotificationParents(NotificationParents notificationParents){
        notificationParentsRepository.save(notificationParents);
    }

}
