package com.example.musespringapi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "musics")
public class Music  {
    
    /** 自動採番ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long musicId;

    /** 曲名 */
    @Column(name = "music_name", columnDefinition = "VARCHAR(45)")
    private String musicName;

    /** アーティスト名 */
    @Column(name = "artist_name", columnDefinition = "VARCHAR(45)")
    private String artistName;

    /** ジャンル名 */
    @Column(name = "genre_name", columnDefinition = "VARCHAR(45)")
    private String genreName;

    /** 曲イメージ画像 */
    @Lob
    @Column(name = "image", columnDefinition = "BLOB")
    private byte[] image;

    /** チェックしたユーザーの配列 */
    
}