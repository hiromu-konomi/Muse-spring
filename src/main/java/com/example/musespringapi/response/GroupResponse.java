package com.example.musespringapi.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupResponse {
    
    private Long groupId;
    private String groupName;
    private String ownerName;
}
