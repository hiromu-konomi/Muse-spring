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

    // ユーザーのグループ参加状況を取得するメソッド
    public Integer getJoinStatus(String userNum, Long groupId) {
        return groupMemberRepository.statusFindbyUserNumAndGroupId(userNum, groupId);
    }

    // ユーザーが参加しているグループを全件取得する
    public List<Long> findByUserNum(String userNum) {
        return groupMemberRepository.findByUserNum(userNum);
    }

    // ユーザーがそのグループに招待されているか確認するメソッド
    public GroupMember findByUserNumAndGroupId(String userNum, Long groupId) {
        return groupMemberRepository.findByUserNumAndGroupId(userNum, groupId);
    }

    // グループIDをもとに参加者を全件取得
    public List<GroupMember> findByGroupId(Long groupId) {
        return groupMemberRepository.findByGroupId(groupId);
    }
}
