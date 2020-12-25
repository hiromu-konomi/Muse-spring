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
@Getter
@Setter
@Table(name = "group_notification")
public class GroupNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer groupNotificationId;

    @Column(name = "group_transfer", columnDefinition = "VARCHAR(225)")
    private String groupTransfer;

    @Column(name = "group_receiver", columnDefinition = "VARCHAR(225)")
    private String groupReceiver;

    @Column(name = "group_id")
    private Long groupId;

}
