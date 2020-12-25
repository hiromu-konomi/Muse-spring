package com.example.musespringapi.repository;

import java.util.List;

import com.example.musespringapi.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FirebaseRepository extends JpaRepository<User, String> {

    List<User> findByUserNum(String userNum);

}
