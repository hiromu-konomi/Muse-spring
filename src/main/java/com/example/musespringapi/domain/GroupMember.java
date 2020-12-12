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
@Table(name = "`group_member`")
public class GroupMember {
    
    /** 自動採番ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupMemberId;

    /** グループID */
    @Column(name = "group_id", columnDefinition = "INT")
    private Long groupId;

    /** 参加者（あるいは招待されてるユーザー）のFirebaseID */
    @Column(name = "user_num", columnDefinition = "VARCHAR(45)")
    private String userNum;

    /** ユーザーの参加状況（１：参加済, ２：招待済） */
    @Column(name = "join_status", columnDefinition = "INT")
    private Integer joinStatus;
}
