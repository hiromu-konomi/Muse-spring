package com.example.musespringapi.repository;

import java.util.List;

import com.example.musespringapi.domain.Music;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends JpaRepository<Music, Integer> {

    Music findByPostId(Integer postId);

}
