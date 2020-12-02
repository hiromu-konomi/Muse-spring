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
@Table(name="`groups`")
public class Group {
    
    /** 自動採番ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;

    /** グループ名 */
    @Column(name = "group_name", columnDefinition = "VARCHAR(45)", nullable = true)
    private String groupName;

    /** グループ説明 */
    @Column(name = "group_description", columnDefinition = "VARCHAR(255)", nullable = true)
    private String groupDescription;

    /** グループ画像 */
    @Lob
    @Column(name = "group_image", columnDefinition = "BLOB", nullable = true)
    private byte[] groupImage;
    
    /** グループ主催者ID */
    @Column(name = "owner_user_id", columnDefinition = "INT", nullable = true)
    private Integer ownerUserId;

    public static Group newGroup(String groupName) {
        Group group = new Group();
        group.groupName = groupName;
        return group;
    }
}
