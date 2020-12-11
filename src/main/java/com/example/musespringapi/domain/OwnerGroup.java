package com.example.musespringapi.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OwnerGroup {
    private String groupName;
    private Integer countMember;
    private Long groupId;
}
