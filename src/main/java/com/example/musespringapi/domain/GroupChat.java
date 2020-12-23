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
@Table(name = "group_chats")
public class GroupChat {
    
    /** 自動採番ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupChatId;
    
    /** グループID */
    @Column(name = "group_id", columnDefinition = "INT")
    private Long groupId;

    /** ユーザーID */
    @Column(name = "user_num", columnDefinition = "VARCHAR(225)")
    private String userNum;

    /** 送信時の日時 */
    @Column(name = "submit_time", columnDefinition = "VARCHAR(225)")
    private String submitTime;

    /** メッセージ内容 */
    @Column(name = "chat_text", columnDefinition = "VARCHAR(225)")
    private String chatText;
}
