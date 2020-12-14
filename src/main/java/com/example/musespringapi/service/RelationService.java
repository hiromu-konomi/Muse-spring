package com.example.musespringapi.service;

import java.util.List;

import com.example.musespringapi.repository.RelationRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RelationService {

    private final RelationRepository relationRepository;

    public List<String> getFollowingUserNum(String userNum) {
        return relationRepository.getFollowingUserNum(userNum);
    }

    // public List<Integer> getPostIdFromFollowingUser(String followingUser) {
    // return relationRepository.getPostIdFromFollowingUser(followingUser);
    // }

}
