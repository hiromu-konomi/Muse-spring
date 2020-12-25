package com.example.musespringapi.service;

import java.util.List;

import com.example.musespringapi.domain.GroupChat;
import com.example.musespringapi.repository.GroupChatRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupChatService {
    
    private final GroupChatRepository groupChatRepository;

    public void save(GroupChat groupChat) {
        groupChatRepository.save(groupChat);
    }

    // グループIDをもとにチャット情報を全件取得
    public List<GroupChat> findByGroupId(Long groupId) {
        return groupChatRepository.findByGroupIdOrderByGroupChatIdDesc(groupId);
    }
}
