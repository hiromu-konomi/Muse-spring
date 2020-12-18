package com.example.musespringapi.repository;

import com.example.musespringapi.domain.GroupNotification;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupNotificationRepository extends JpaRepository<GroupNotification, Integer> {



}
