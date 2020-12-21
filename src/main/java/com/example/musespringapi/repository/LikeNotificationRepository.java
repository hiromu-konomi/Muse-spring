package com.example.musespringapi.repository;

import java.util.List;

import com.example.musespringapi.domain.LikeNotification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeNotificationRepository extends JpaRepository<LikeNotification, Integer> {

    @Query(value = "SELECT * FROM like_notification WHERE like_receiver = ?1 ORDER BY like_notification_id DESC", nativeQuery = true)
    List<LikeNotification> getLikeNotificationListByReceiver(String likeReceiver);

}
