package com.loonds.StaySync.model.dto;

import com.loonds.StaySync.model.entity.Booking;
import com.loonds.StaySync.model.entity.CheckInOutRecord;
import com.loonds.StaySync.model.entity.Room;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class BookingDto {
    long id;
    private List<RoomDto> rooms;
    private String guestName;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;


    public static BookingDto of(Booking booking){
        BookingDto output = new BookingDto();
        output.setId(booking.getId());
        output.setGuestName(booking.getGuest().getFirstName() +"-"+ booking.getGuest().getLastName());
        output.setCheckIn(booking.getCheckInOutRecord().getIn());
        output.setCheckOut(booking.getCheckInOutRecord().getOut());
        return output;
    }
}
