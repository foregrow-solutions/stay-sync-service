package com.loonds.StaySync.model.dto;

import com.loonds.StaySync.model.entity.Guest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuestDto {
    String id;
    String firstName;
    String lastName;
    String email;
    String mobile;

    public static GuestDto of (Guest guest){
        GuestDto output = new GuestDto();
        output.setId(guest.getId());
        output.setFirstName(guest.getFirstName());
        output.setLastName(guest.getLastName());
        output.setEmail(guest.getEmail());
        output.setMobile(guest.getMobile());
        return output;
    }
}
