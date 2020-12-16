package com.example.musespringapi.response;

import java.util.List;

import com.example.musespringapi.domain.JoinGroup;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JoinGroupResponse {
    private List<JoinGroup> joinGroups;
}
