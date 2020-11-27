package com.example.musespringapi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "posts")
public class Post {
    
    /** 自動採番ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    /** ユーザーID */
    @Column(name = "user_id", columnDefinition = "INT")
    private Integer userId;

    /** ミュージックID */
    @Column(name = "music_id", columnDefinition = "INT")
    private Integer musicID;

    /** 投稿テキスト */
    @Column(name = "post_text", columnDefinition = "VARCHAR(255)")
    private String postText;
}
