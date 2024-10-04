package com.loonds.StaySync.model.entity;

import com.loonds.StaySync.model.enums.NotificationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@Getter
@Setter
@Entity
public class Notification {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private User user;

    private String title;

    private String message;

    private Boolean isOpen;

    @Enumerated(EnumType.STRING)
    NotificationType type;

    @CreatedDate
    private Instant createdDate;
    @LastModifiedDate
    private Instant modifiedDate;
}
