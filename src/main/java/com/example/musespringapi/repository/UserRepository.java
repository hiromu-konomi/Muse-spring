package com.example.musespringapi.repository;

import com.example.musespringapi.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    
}
