package com.example.musespringapi.repository;

import java.util.List;

import com.example.musespringapi.domain.GroupMember;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {
    
    // グループに参加しているメンバー数を取得するメソッド
    @Query(countQuery = "SELECT count(`user_num`) FROM `group_member` WHERE `group_id` = ?1", nativeQuery = true)
    Integer countMemberFindByGroupId(Long groupId);

    // ユーザーのグループ参加状況を取得するメソッド
    @Query(value = "SELECT `join_status` FROM `group_member` WHERE `user_num` = ?1 AND `group_id` = ?2", nativeQuery = true)
    Integer statusFindbyUserNumAndGroupId(String userNum, Long groupId);

    // ユーザーが参加しているグループのIDを全件取得するメソッド
    @Query(value = "SELECT `group_id` FROM `group_member` WHERE `user_num` = ?1", nativeQuery = true)
    List<Long> findByUserNum(String userNum);

    // ユーザーがそのグループに既に招待されているか確認するメソッド
    @Query(value = "SELECT * FROM `group_member` WHERE `user_num` = ?1 AND `group_id` = ?2", nativeQuery = true)
    GroupMember findByUserNumAndGroupId(String userNum, Long groupId);

}
