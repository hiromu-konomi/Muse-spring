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
@Table(name="`groups`")
public class Group {

    /** 自動採番ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;

    /** グループ名 */
    @Column(name = "group_name", columnDefinition = "VARCHAR(45)")
    private String groupName;

    /** グループ説明 */
    @Column(name = "group_description", columnDefinition = "VARCHAR(255)")
    private String groupDescription;

    /** グループ管理者ID */
    @Column(name = "owner_user_id", columnDefinition = "VARCHAR(45)")
    private String ownerUserId;
}
