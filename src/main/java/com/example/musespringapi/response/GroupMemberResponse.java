package com.example.musespringapi.response;

import java.util.List;

import com.example.musespringapi.domain.User;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GroupMemberResponse {
    private List<User> users;
}
