package com.example.musespringapi.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
public class User {

    /** 自動採番ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    /** ユーザー名 */
    private String userName;
    /** プロフィール画像 */
    private String profilePicture;
    /** ヘッダー画像 */
    private String profileHeadPicture;
    /** プロフィール */
    private String profile;
    /** 部署ID */
    private Integer depId;
    /** 入社年月 */
    private Date hireDate;
}
