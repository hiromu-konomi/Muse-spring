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
@Table(name = "`likes`")

public class Like {

    /** 自動採番ID */
    @Id
    @Column(name = "like_id", columnDefinition = "INT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer likeId;

    /** userNum */
    @Column(name = "user_num", columnDefinition = "VARCHAR(225)")
    private String userNum;

    /** postId */
    @Column(name = "post_id", columnDefinition = "INT")
    private Integer postId;
}
