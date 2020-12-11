package com.example.musespringapi.service;

import java.util.List;

import com.example.musespringapi.domain.Group;
import com.example.musespringapi.repository.GroupRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public void save(Group group) {
        groupRepository.save(group);
    }

    public Long newGroupId(String userNum) {
        return groupRepository.getMaxId(userNum);
    }

    public List<Group> ownerGroupList(String userNum) {
        return groupRepository.groupList(userNum);
    }
}