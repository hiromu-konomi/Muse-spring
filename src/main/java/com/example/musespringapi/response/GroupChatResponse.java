package com.example.musespringapi.response;

import java.util.List;

import com.example.musespringapi.domain.GroupChat;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GroupChatResponse {
    private List<GroupChat> groupChats;
}
