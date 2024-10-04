package com.loonds.StaySync.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemName;
    private String description;
    private String imageUrl;
    private Double price;

    @ManyToOne
    private Channel channel;

    @OneToMany(mappedBy = "menu")
    private Set<OrderItem> orderItems;

    private boolean isAvailable;
}
