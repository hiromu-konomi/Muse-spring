package com.example.musespringapi.service;

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

    public Long newGroupId() {
        return groupRepository.getMaxId();
    }
}