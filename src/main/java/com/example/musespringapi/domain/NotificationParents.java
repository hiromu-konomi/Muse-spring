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
@Table(name="`notification_parents`")
public class NotificationParents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer notificationParentsId;

    @Column(name = "type_number")
    private Integer typeNumber;

}
