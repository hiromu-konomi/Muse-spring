package com.example.musespringapi.repository;

import com.example.musespringapi.domain.NotificationParents;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationParentsRepository extends JpaRepository<NotificationParents, Integer> {

}
