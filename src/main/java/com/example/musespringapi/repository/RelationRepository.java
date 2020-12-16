package com.example.musespringapi.repository;

import java.util.List;

import com.example.musespringapi.domain.Relation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationRepository extends JpaRepository<Relation, Integer> {

    @Query(value = "SELECT `follower_user_num` FROM `relation` WHERE `following_user_num` = ?1", nativeQuery = true)
    List<String> getFollowerUserNum(String userNum);

}
