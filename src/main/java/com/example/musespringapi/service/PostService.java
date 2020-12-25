package com.example.musespringapi.service;

import java.util.List;

import com.example.musespringapi.domain.Like;
import com.example.musespringapi.domain.Music;
import com.example.musespringapi.domain.Post;
import com.example.musespringapi.repository.LikeRepository;
import com.example.musespringapi.repository.MusicRepository;
import com.example.musespringapi.repository.PostRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MusicRepository musicRepository;
    private final LikeRepository likeRepository;

    public void insertMusic(Music music) {
        System.out.println(music.getGenreName());
        musicRepository.save(music);

    }

    public void insertPost(Post post) {
        postRepository.save(post);
    }

    public List<Post> findByUserNum(String userNum) {
        return postRepository.findByUserNum(userNum);
    }

    public List<Post> getPostIdFromFollowingUser(String followingUser) {
        return postRepository.getPostIdFromFollowingUser(followingUser);
    }

    public void deletePost(Integer postId) {
        postRepository.deletePost(postId);
    }

    public void deleteMusic(Integer postId) {
        musicRepository.deleteMusic(postId);
    }

    public Post getPostByPostId(Integer postId){
        List<Post> postList = postRepository.findByPostId(postId);

        if(postList.isEmpty()) {
            return null;
        }
        return postList.get(0);
    }
    // public List<Post> selectPost(String userId) {

    // EntityManagerFactory emf = P

    // List<Post> postLists = entityManager
    // .createNativeQuery("SELECT artist_name, music_name, post_text"
    // + "FROM posts p INNER JOIN musics m ON p.music_id = m.music_id"
    // + "INNER JOIN users u ON p.user_id = u.user_id"
    // + "WHERE user_id = :userId", Post.class)
    // .setParameter("userId", userId)
    // .getResultList();

    // }

    public List<Like> getLikePosts(String userNum){
        List<Like> likeList = likeRepository.findByUserNum(userNum);

        if(likeList.isEmpty()) {
            return null;
        }
        return likeList;
    }

}
