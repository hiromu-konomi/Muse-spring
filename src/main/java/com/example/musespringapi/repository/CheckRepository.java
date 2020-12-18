package com.example.musespringapi.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.example.musespringapi.domain.Check;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckRepository extends JpaRepository<Check, Integer> {

    @Query(value = "SELECT COUNT(`music_id`) FROM `checks` WHERE `music_id` = ?1", nativeQuery = true)
    Integer checkCount(Integer musicId);

    // musicIdを取ってくる処理
    @Query(value = "SELECT `music_id` FROM `musics` WHERE `post_id` = ?1", nativeQuery = true)
    Integer musicId(Integer musicId);

    // musicIdとuserNumからテーブルに情報があるかの確認
    @Query(value = "SELECT * FROM `checks` WHERE `user_num` = ?1 AND `music_id` = ?2", nativeQuery = true)
    Check checkList(String userNum, Integer musicId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM `checks` WHERE `music_id` = ?1 AND `user_num` = ?2", nativeQuery = true)
    void deleteChecks(Integer musicId, String userNum);
    
    List<Check> findByUserNum(String userNum);
}
