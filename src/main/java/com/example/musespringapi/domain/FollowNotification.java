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
@Table(name = "`follow_notification`")
public class FollowNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer followNotificationId;

    @Column(name = "follow_transfer", columnDefinition = "VARCHAR(225)")
    private String followTransfer;

    @Column(name = "follow_receiver", columnDefinition = "VARCHAR(225)")
    private String followReceiver;

    @Column(name = "type_number")
    private Integer typeNumber;

}
