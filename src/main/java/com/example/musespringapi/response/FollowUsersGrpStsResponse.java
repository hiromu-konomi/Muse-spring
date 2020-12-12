package com.example.musespringapi.response;

import java.util.List;

import com.example.musespringapi.domain.FollowUserGrpSts;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FollowUsersGrpStsResponse {
    private List<FollowUserGrpSts> followUsers;
}
