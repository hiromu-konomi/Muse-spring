package com.example.musespringapi.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinGroup {
    private String groupName;
    private String ownerName;
    private Long groupId;
}
