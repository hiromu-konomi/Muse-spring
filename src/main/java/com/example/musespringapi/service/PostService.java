package com.example.musespringapi.service;

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


    public void insertMusic(Music music) {

        // Music music = new Music();
        // Post post = new Post();
        // music.setArtistName(info.getSearched_artist_name());
        // music.setMusicName(info.getSearched_song());
        // music.setImage(info.getSearched_picture());
        // music.setGenreId(info.getValue());
        // music.setGenreName(info.getLabel());

        // music.setArtistName(musics.getSearched_artist_name());
        // music.setMusicName(musics.getSearched_song());
        // music.setImage(musics.getSearched_picture());
        // music.setGenreId(form.getValue());
        // music.setGenreName(form.getLabel());

        // post.setPostText(form.getReview());

        musicRepository.save(music);
       
    }

    public void insertPost(Post post) {

        // Music music = new Music();
        // Post post = new Post();
        // music.setArtistName(info.getSearched_artist_name());
        // music.setMusicName(info.getSearched_song());
        // music.setImage(info.getSearched_picture());
        // music.setGenreId(info.getValue());
        // music.setGenreName(info.getLabel());

        // music.setArtistName(musics.getSearched_artist_name());
        // music.setMusicName(musics.getSearched_song());
        // music.setImage(musics.getSearched_picture());
        // music.setGenreId(form.getValue());
        // music.setGenreName(form.getLabel());

        // post.setPostText(form.getReview());

        postRepository.save(post);
        

    }

}
