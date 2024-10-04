package com.loonds.StaySync.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.loonds.StaySync.model.enums.DocType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Guest {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String docCid;
    private String docUrl;

    @Enumerated(EnumType.STRING)
    private DocType type;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Booking> bookings = new HashSet<>();

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Order> order = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "channel_id")
    private Channel channel;

    @CreatedDate
    private Instant createdDate;
    @LastModifiedDate
    private Instant modifiedDate;
}
