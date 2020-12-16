package com.example.musespringapi.service;

import java.util.List;

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

    // ユーザーのグループ参加状況を取得するメソッド
    public Integer getJoinStatus(String userNum, Long groupId) {
        return groupMemberRepository.statusFindbyUserNumAndGroupId(userNum, groupId);
    }

    // ユーザーが参加しているグループを全件取得する
    public List<Long> findByUserNum(String userNum) {
        return groupMemberRepository.findByUserNum(userNum);
    }
}
