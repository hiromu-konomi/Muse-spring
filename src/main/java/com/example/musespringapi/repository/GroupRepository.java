package com.example.musespringapi.repository;

import com.example.musespringapi.domain.Group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    
    @Query(value = "SELECT MAX(`group_id`) FROM `groups` WHERE `owner_user_id` = ?1", nativeQuery = true)
    Long getMaxId(String userNum);
}