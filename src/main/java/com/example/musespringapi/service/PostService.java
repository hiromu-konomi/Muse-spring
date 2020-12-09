package com.example.musespringapi.service;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import com.example.musespringapi.domain.Music;
import com.example.musespringapi.domain.Post;
import com.example.musespringapi.repository.MusicRepository;
import com.example.musespringapi.repository.PostRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MusicRepository musicRepository;

    public void insertPost(String postText, String artistName, String musicName, String userId)  {

        Post post = new Post();
        Music music = new Music();
        Integer postUserId = Integer.valueOf(userId);

        post.setPostText(postText);
        post.setUserId(postUserId);
        postRepository.save(post);

        music.setArtistName(artistName);
        music.setMusicName(musicName);
        musicRepository.save(music);

    }

    // public List<Post> selectPost(String userId) {

    //     EntityManagerFactory emf = P

    //     List<Post> postLists = entityManager
    //     .createNativeQuery("SELECT artist_name, music_name, post_text"
    //     + "FROM posts p INNER JOIN musics m ON p.music_id = m.music_id"
    //     + "INNER JOIN users u ON p.user_id = u.user_id"
    //     + "WHERE user_id = :userId", Post.class)
    //     .setParameter("userId", userId)
    //     .getResultList();

    // }

}
