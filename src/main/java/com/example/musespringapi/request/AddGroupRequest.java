package com.example.musespringapi.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddGroupRequest {
    private String groupName;
    private String userNum;
    private List<String> inviteUsers;
}
