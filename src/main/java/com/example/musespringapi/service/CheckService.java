package com.example.musespringapi.service;

import com.example.musespringapi.domain.Check;
import com.example.musespringapi.domain.Music;
import com.example.musespringapi.repository.CheckRepository;
import com.example.musespringapi.repository.MusicRepository;

import java.util.List;



import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CheckService {

    private final CheckRepository checkRepository;
    private final MusicRepository musicRepository;

    public void insertChecks(Check check) {
        checkRepository.save(check);
    }

    public Integer getMusicId(Integer postId) {
        return checkRepository.musicId(postId);
    }

    public Integer checkCount(Integer musicId) {
        return checkRepository.checkCount(musicId);
    }

    public Check checkList(String userNum, Integer musicId) {
        return checkRepository.checkList(userNum, musicId);
    }

    public void deleteChecks(Integer musicId, String userNum) {
        checkRepository.deleteChecks(musicId, userNum);
    }
    
    public List<Check> getCheckSongsByUserNum(String userNum){
    	List<Check> checkList = checkRepository.findByUserNum(userNum);
    	
    	if(checkList.isEmpty()) {
    		return null;
    	}
    	
    	return checkList;
    }
    
    public Music getMusicsByMusicId(Integer musicId){
    	List<Music> musicList = musicRepository.findByMusicId(musicId);
    	
    	if(musicList.isEmpty()) {
    		return null;
    	}
    	return musicList.get(0);
    }
}
