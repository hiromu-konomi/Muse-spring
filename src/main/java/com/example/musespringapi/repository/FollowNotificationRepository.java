package com.example.musespringapi.repository;

import java.util.List;

import com.example.musespringapi.domain.FollowNotification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowNotificationRepository extends JpaRepository<FollowNotification, Integer> {

    @Query(value = "SELECT * FROM follow_notification WHERE follow_receiver = ?1 ORDER BY follow_notification_id DESC", nativeQuery = true)
    List<FollowNotification> getFollowNotificationByReceiver(String followReceiver);

}
