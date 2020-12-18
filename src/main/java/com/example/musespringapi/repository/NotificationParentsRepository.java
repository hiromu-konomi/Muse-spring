package com.example.musespringapi.repository;

import com.example.musespringapi.domain.NotificationParents;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationParentsRepository extends JpaRepository<NotificationParents, Integer> {

    @Query(value = "SELECT * FROM notification_parents WHERE notification_parents_id = ?1", nativeQuery = true)
    NotificationParents getNotiPareByNotiPareId(Integer notiPareId);

}
