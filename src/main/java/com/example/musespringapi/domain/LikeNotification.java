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
@Table(name = "like_notification")
public class LikeNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer likeNotificationId;

    @Column(name = "like_transfer", columnDefinition = "VARCHAR(225)")
    private String likeTransfer;

    @Column(name = "like_receiver", columnDefinition = "VARCHAR(225)")
    private String likeReceiver;

}
