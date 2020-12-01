package com.example.musespringapi.repository;

import com.example.musespringapi.domain.Group;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Integer> {
    
}