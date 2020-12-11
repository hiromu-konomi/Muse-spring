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
@Table(name = "`relation`")
public class Relation {

    /** 自動採番ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer relationId;

    @Column(name = "following_user_num", columnDefinition = "VARCHAR(225)")
    private String followingUserNum;

    @Column(name = "follower_user_num", columnDefinition = "VARCHAR(225)")
    private String followerUserNum;
}
