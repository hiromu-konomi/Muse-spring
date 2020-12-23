package com.example.musespringapi.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InviteGroupRequest {
    private String transferUser;
    private List<String> receiverUsers;
    private Long groupId;
}
