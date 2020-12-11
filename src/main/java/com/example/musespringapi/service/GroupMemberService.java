package com.example.musespringapi.service;

import com.example.musespringapi.domain.GroupMember;
import com.example.musespringapi.repository.GroupMemberRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupMemberService {
    
    private final GroupMemberRepository groupMemberRepository;

    public void save(GroupMember groupMember) {
        groupMemberRepository.save(groupMember);
    }

    public Integer countMember(Long groupId) {
        return groupMemberRepository.countMemberFindByGroupId(groupId);
    }
}
