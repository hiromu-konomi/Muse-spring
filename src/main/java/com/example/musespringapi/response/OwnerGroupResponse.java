package com.example.musespringapi.response;

import java.util.List;

import com.example.musespringapi.domain.OwnerGroup;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OwnerGroupResponse {
    
    private List<OwnerGroup> ownerGroups;
}
