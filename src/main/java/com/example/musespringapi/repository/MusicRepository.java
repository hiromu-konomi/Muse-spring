package com.example.musespringapi.repository;

import java.util.List;

import com.example.musespringapi.domain.Music;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends JpaRepository<Music, Integer> {

    Music findByPostId(Integer postId);

    //musicNameをもとにmusicId,MusicName,artistNameを検索
    @Query(value = "SELECT * FROM `musics` WHERE `artist_name` LIKE %?1% ORDER BY `post_id` DESC", nativeQuery = true)
    List<Music> findByArtistNameStartsWith(String searchPost);

}
