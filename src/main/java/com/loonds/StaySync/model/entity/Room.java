package com.loonds.StaySync.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String roomNumber;

    private Double pricePerNight;
    @ManyToOne
    private Channel channel;
    @ManyToOne
    private Booking booking;

    private boolean isAvailable;
}
