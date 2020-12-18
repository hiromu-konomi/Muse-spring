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
@Table(name = "`checks`")
public class Check {

    /** 自動採番ID */
    @Id
    @Column(name = "check_id", columnDefinition = "INT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer checkId;

    /** userNum */
    @Column(name = "user_num", columnDefinition = "VARCHAR(225)")
    private String userNum;

    /** musicId */
    @Column(name = "music_id", columnDefinition = "INT")
    private Integer musicId;

}
