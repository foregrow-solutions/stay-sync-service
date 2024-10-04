package com.loonds.StaySync.service.impl;

import com.loonds.StaySync.common.exception.CustomExceptions;
import com.loonds.StaySync.common.exception.ErrorCode;
import com.loonds.StaySync.model.dto.BookingDto;
import com.loonds.StaySync.model.dto.RoomDto;
import com.loonds.StaySync.model.entity.*;
import com.loonds.StaySync.repository.BookingRepository;
import com.loonds.StaySync.repository.ChannelRepository;
import com.loonds.StaySync.repository.GuestRepository;
import com.loonds.StaySync.repository.RoomRepository;
import com.loonds.StaySync.service.BookingService;
import com.loonds.StaySync.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final ChannelRepository channelRepository;
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;
    private final GuestRepository guestRepository;

    @Override
    @Transactional
    public BookingDto bookRoom(String channelId, String guestId, long[] roomIds, CheckInOutRecord inOutRecord) {
        Channel channel = channelRepository.findById(channelId)
                .orElseThrow(() -> new CustomExceptions.ResourceNotFoundException(ErrorCode.NOT_FOUND, "Channel Not Found"));
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new CustomExceptions.ResourceNotFoundException(ErrorCode.NOT_FOUND, "Guest Not Found"));

        // Retrieve all rooms by their IDs
        List<Room> rooms = Arrays.stream(roomIds)
                .mapToObj(roomRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        Booking booking = new Booking();
        booking.setGuest(guest);
        booking.setRooms(rooms);
        booking.setChannel(channel);
        booking.setCheckInOutRecord(inOutRecord);
        Booking save = bookingRepository.save(booking);
        return BookingDto.of(save);
    }

    @Override
    public Optional<BookingDto> updateBooking(long bookingId) {
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoomDto> getListBookedRooms(String channelId) {
//        List<Room> bookedRooms = bookingRepository.findBookedRoomsByChannel(channelId);
//        return bookedRooms.stream()
//                .map(RoomDto::of)
//                .collect(Collectors.toList());
        List<RoomDto> list = roomRepository.findAll().stream().map(RoomDto::of).toList();

        return list;
    }

    @Override
    public Optional<BookingDto> getBookingDetails(long bookingId) {
        return Optional.empty();
    }

    @Override
    public Page<BookingDto> getAllBookingsByChannel(String channelId, Pageable pageable) {
        Optional<Channel> channel = channelRepository.findById(channelId);
        return bookingRepository.findAllByChannel(channel.get(), pageable).map(BookingDto::of);
    }

    @Override
    public Page<BookingDto> getAllBookingsByGuestAndChannel(String channelId, String guestId, Pageable pageable) {
        return null;
    }

    @Override
    public void cancelBooking(long bookingId) {

    }
}
