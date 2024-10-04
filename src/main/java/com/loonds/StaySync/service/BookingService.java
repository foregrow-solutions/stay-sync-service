package com.loonds.StaySync.service;

import com.loonds.StaySync.model.dto.BookingDto;
import com.loonds.StaySync.model.dto.RoomDto;
import com.loonds.StaySync.model.entity.CheckInOutRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingService {
    BookingDto bookRoom(String channelId, String guestId, long[] roomIds, CheckInOutRecord inOutRecord);

    Optional<BookingDto> updateBooking(long bookingId);

    List<RoomDto> getListBookedRooms(String channelId);

    Optional<BookingDto> getBookingDetails(long bookingId);

    Page<BookingDto> getAllBookingsByChannel(String channelId, Pageable pageable);

    Page<BookingDto> getAllBookingsByGuestAndChannel(String channelId, String guestId, Pageable pageable);

    void cancelBooking(long bookingId);
}
