package com.example.musespringapi.repository;

import java.util.List;

import com.example.musespringapi.domain.Group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    
    // 作成したグループのIDを取得する（作成者のFirebaseIDをもとにgroup_idの最大値を取得）
    @Query(value = "SELECT MAX(`group_id`) FROM `groups` WHERE `owner_user_id` = ?1", nativeQuery = true)
    Long getMaxId(String userNum);

    // 管理しているグループのグループIDとグループ名を全件取得する
    @Query(value = "SELECT * FROM `groups` WHERE `owner_user_id` = ?1 ORDER BY `group_id` DESC", nativeQuery = true)
    List<Group> groupList(String userNum);
}