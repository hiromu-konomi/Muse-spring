package com.example.musespringapi.repository;

import java.util.List;

import com.example.musespringapi.domain.GroupNotification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupNotificationRepository extends JpaRepository<GroupNotification, Integer> {

    @Query(value = "SELECT * FROM group_notification WHERE group_receiver = ?1 ORDER BY group_notification_id DESC", nativeQuery = true)
    List<GroupNotification> getGroupNotificationByReceiver(String receiver);

}
