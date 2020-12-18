package com.example.musespringapi.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.example.musespringapi.domain.Relation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationRepository extends JpaRepository<Relation, Integer> {

    @Query(value = "SELECT `follower_user_num` FROM `relation` WHERE `following_user_num` = ?1", nativeQuery = true)
    List<String> getFollowerUserNum(String userNum);
    
//    //フォロー解除した時の処理をするため
//    List<Relation> findByFollowingUserNum(String followingUserNum);
    
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM `relation` WHERE `following_user_num` = ?1 AND `follower_user_num` = ?2", nativeQuery = true)
    void DeleteFollowUser(String followingUserNum, String followerUserNum);

    
   
//    @Query(value = "SELECT * FROM `relation` WHERE `following_user_num` = ?1 AND `follower_user_num` = ?2", nativeQuery = true)
//    Relation findByFollowingUserNumAndFollowerUserNum(String followingUserNum, String followerUserNum);
   
    //自分がフォローしているユーザーを取ってくる
   
    List<Relation> findByFollowingUserNum(String followingUserNum);
    
    
    //フォローされているユーザーを取ってくる
    List<Relation> findByFollowerUserNum(String followerUserNum);
    
    
}
