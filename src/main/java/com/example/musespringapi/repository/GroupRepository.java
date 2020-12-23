package com.example.musespringapi.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.example.musespringapi.domain.Group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    // 作成したグループのIDを取得する（作成者のFirebaseIDをもとにgroup_idの最大値を取得）
    @Query(value = "SELECT MAX(`group_id`) FROM `groups` WHERE `owner_user_id` = ?1", nativeQuery = true)
    Long getMaxId(String userNum);

    // 管理しているグループ一覧を管理者のユーザーIDをもとに全件取得する
    @Query(value = "SELECT * FROM `groups` WHERE `owner_user_id` = ?1 ORDER BY `group_id` DESC", nativeQuery = true)
    List<Group> groupList(String userNum);

    // 管理しているグループ一覧を管理者のユーザーIDと検索ワードをもとに全件取得する
    @Query(value = "SELECT * FROM `groups` WHERE `owner_user_id` = ?1 AND `group_name` LIKE %?2%", nativeQuery = true)
    List<Group> groupListByWord(String userNum, String searchWord);

    // 参加しているグループ一覧をグループのIDと検索ワードをもとに一件かnullを取得する
    @Query(value = "SELECT * FROM `groups` WHERE `group_id` = ?1 AND `group_name` LIKE %?2%", nativeQuery = true)
    Group findByIdAndName(Long groupId, String searchWord);

    //グループを削除する
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM `groups` WHERE `group_id` = ?1", nativeQuery = true)
    void deleteGroups(Long groupId);

    //グループメンバーを削除
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM `group_member` WHERE `group_id` = ?1", nativeQuery = true)
    void deleteGroupMember(Long groupId);

    //自分が参加してるグループを抜ける
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM `group_member` WHERE `group_id` = ?1 AND `user_num` = ?2", nativeQuery = true)
    void deleteJoinGroup(Long groupId, String userNum);

    @Query(value = "SELECT * FROM `groups` WHERE `group_name` LIKE %?1%", nativeQuery = true)
    List<Group> findGroupsByGroupNameStartsWith(String groupName);

}