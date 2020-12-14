package com.example.musespringapi.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "`musics`")
public class Music {

    /** 自動採番ID */
    @Id
    @Column(name = "music_id", columnDefinition = "INT")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer musicId;

    /** 曲名 */
    @Column(name = "music_name", columnDefinition = "VARCHAR(45)")
    private String musicName;

    /** アーティスト名 */
    @Column(name = "artist_name", columnDefinition = "VARCHAR(45)")
    private String artistName;

    // /** ジャンルID */
    // @Column(name = "genre_id", columnDefinition = "VARCHAR(45)")
    // private Integer genreId;

    /** ジャンル名 */
    @Column(name = "genre_name", columnDefinition = "VARCHAR(45)")
    private String genreName;

    /** 曲イメージ画像 */
    @Lob
    @Column(name = "image", columnDefinition = "VARCHAR(225)")
    private String image;

    /** postId */
    @Column(name = "post_id", columnDefinition = "INT")
    private Integer postId;

    // @OneToMany(mappedBy = "user")
    // private List<Post> postListWhomUser;

    /** チェックしたユーザーの配列 */

}
