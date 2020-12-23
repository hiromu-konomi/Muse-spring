package com.example.musespringapi.repository;

import java.util.List;

import com.example.musespringapi.domain.GroupChat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupChatRepository extends JpaRepository<GroupChat, Long> {
    
    List<GroupChat> findByGroupIdOrderByGroupChatIdDesc(Long groupId);
}