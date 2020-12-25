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

    // ユーザーが管理しているグループを全件取得
    public List<Group> ownerGroupList(String userNum) {
        return groupRepository.groupList(userNum);
    }

    // ユーザーが管理しているグループを検索ワードをもとに全件取得
    public List<Group> ownerGroupListByWord(String userNum, String searchWord) {
        return groupRepository.groupListByWord(userNum, searchWord);
    }

    // 主キーをもとに検索
    public Group findByGroupId(Long groupId) {
        return groupRepository.getOne(groupId);
    }

    // 主キーとワードをもとに検索
    public Group findByIdAndSearchWord(Long groupId, String searchWord) {
        return groupRepository.findByIdAndName(groupId, searchWord);
    }

    public void deleteGroups(Long groupId) {
        groupRepository.deleteGroups(groupId);
    }

    public void deleteGroupMember(Long groupId) {
        groupRepository.deleteGroupMember(groupId);
    }

    public void deleteJoinGroup(Long groupId, String userNum){
        groupRepository.deleteJoinGroup(groupId, userNum);
    }

}