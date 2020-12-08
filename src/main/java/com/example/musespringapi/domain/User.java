package com.example.musespringapi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "users")
public class User {

    /** 自動採番 */
    @Id
    @Column(name = "user_id", columnDefinition = "INT)")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "user_num", columnDefinition = "VARCHAR(225)")
    private String userNum;

    /** ユーザー名 */
    @Column(name = "user_name", columnDefinition = "VARCHAR(225)")
    private String userName;

    /** プロフィール */
    @Column(name = "profile", columnDefinition = "VARCHAR(255)")
    private String profile;

    /** 部署ID */
    @Column(name = "dep_id", columnDefinition = "INT")
    private Integer depId;

    /** 入社年月 */
    @Column(name = "hire_date", columnDefinition = "DATE")
    private Date hireDate;

    /** ポストテーブルとの結合 */
    // @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    // private List<Post> postListWhomUser;

}
