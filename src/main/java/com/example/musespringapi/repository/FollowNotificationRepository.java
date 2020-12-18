package com.example.musespringapi.repository;

import com.example.musespringapi.domain.FollowNotification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowNotificationRepository extends JpaRepository<FollowNotification, Integer> {
    
}
