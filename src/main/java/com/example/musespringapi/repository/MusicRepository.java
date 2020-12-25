package com.example.musespringapi.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.example.musespringapi.domain.Music;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends JpaRepository<Music, Integer> {

    // postIdからmusicの情報を取ってくる処理
    @Query(value = "SELECT * FROM `musics` WHERE `post_id` = ?1", nativeQuery = true)
    Music getMusicInfo(Integer postId);

    // musicNameをもとにmusicId,MusicName,artistNameを検索
    @Query(value = "SELECT * FROM `musics` WHERE `artist_name` LIKE %?1% OR `music_name` LIKE %?1%", nativeQuery = true)
    List<Music> findByArtistNameStartsWith(String searchPost);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM `musics` WHERE `post_id` = ?1", nativeQuery = true)
    void deleteMusic(Integer postId);

    List<Music> findByMusicId(Integer musicId);
}
