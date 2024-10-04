package com.loonds.StaySync.model.dto;

import com.loonds.StaySync.model.entity.Room;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomDto {
    long id;
    String roomNumber;
    Double pricePerNight;

    public static RoomDto of(Room room){
        RoomDto output = new RoomDto();
        output.setId(room.getId());
        output.setRoomNumber(room.getRoomNumber());
        output.setPricePerNight(room.getPricePerNight());
        return output;
    }
}
