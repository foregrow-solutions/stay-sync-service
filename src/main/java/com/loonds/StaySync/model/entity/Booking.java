package com.loonds.StaySync.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

    @Getter
    @Setter
    @Entity
    public class Booking {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @OneToMany(mappedBy = "booking", cascade = CascadeType.PERSIST)
        private List<Room> rooms;

        @ManyToOne
        @JoinColumn(name = "guest_id")
        private Guest guest;

        @ManyToOne
        @JoinColumn(name = "channel_id")
        private Channel channel;

        @Embedded
        @AttributeOverrides({
                @AttributeOverride(name = "in", column = @Column(name = "check_in")),
                @AttributeOverride(name = "out", column = @Column(name = "check_out"))
        })
        private CheckInOutRecord checkInOutRecord;

    }
