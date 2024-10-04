package com.loonds.StaySync.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Embeddable
public class CheckInOutRecord {

    private LocalDateTime in;
    private LocalDateTime out;
}
