package com.example.musespringapi.service;

import com.example.musespringapi.domain.Check;
import com.example.musespringapi.repository.CheckRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CheckService {

    private final CheckRepository checkRepository;

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
}
