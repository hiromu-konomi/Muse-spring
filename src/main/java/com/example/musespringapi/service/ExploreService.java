package com.example.musespringapi.service;

import java.util.List;

import com.example.musespringapi.domain.Music;
import com.example.musespringapi.repository.MusicRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExploreService {

    private final MusicRepository musicRepository;

    public List<Music> postIdFindByMusicName(String searchPost){
        System.out.println(searchPost);
        return musicRepository.findByArtistNameStartsWith(searchPost);
    }

}
