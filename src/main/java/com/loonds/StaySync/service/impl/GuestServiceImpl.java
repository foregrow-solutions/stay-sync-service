package com.loonds.StaySync.service.impl;

import com.loonds.StaySync.common.exception.CustomExceptions;
import com.loonds.StaySync.common.exception.ErrorCode;
import com.loonds.StaySync.controller.payload.GuestCheckInPayload;
import com.loonds.StaySync.controller.payload.GuestPayload;
import com.loonds.StaySync.event.BookingEvent;
import com.loonds.StaySync.model.dto.GuestDto;
import com.loonds.StaySync.model.entity.*;
import com.loonds.StaySync.repository.*;
import com.loonds.StaySync.service.GuestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GuestServiceImpl implements GuestService {
    private final GuestRepository guestRepository;
    private final ChannelRepository channelRepository;
    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher eventListener;

    @Override
    @Transactional
    public GuestDto create(String channelId, GuestPayload payload) {
        Channel channel = channelRepository.findById(channelId)
                .orElseThrow(() -> new CustomExceptions.ResourceNotFoundException(ErrorCode.NOT_FOUND, "Channel Not Found"));
        Guest guest = new Guest();
        guest.setFirstName(payload.firstName());
        guest.setLastName(payload.lastName());
        guest.setMobile(payload.mobile());
        guest.setChannel(channel);
        Guest save = guestRepository.save(guest);
        BookingEvent bookingEvent = new BookingEvent(channel.getId(), save.getId(), payload.roomIds(), payload.inOutRecord());
        eventListener.publishEvent(bookingEvent);
        return GuestDto.of(save);

    }

//    @Override
//    @Transactional
//    public void checkIn(String guestId, GuestCheckInPayload payload) {
//        Room room = roomRepository.findById(payload.roomId())
//                .orElseThrow(() -> new CustomExceptions.ResourceNotFoundException(ErrorCode.NOT_FOUND, "Room not found"));
//
//        guestRepository.findById(guestId).ifPresent(guest -> {
//            CheckInOutRecord checkInOutRecord = createCheckInOutRecord(room);
//            guest.setCheckInOutRecord(checkInOutRecord);
//        });
//    }
//
//    private CheckInOutRecord createCheckInOutRecord(Room room) {
//        CheckInOutRecord checkInOutRecord = new CheckInOutRecord();
//        checkInOutRecord.setRoom(room);
//        checkInOutRecord.setCheckInDate(Instant.now());
//        CheckInOutRecord save = inOutRecordRepository.save(checkInOutRecord);
//        return save;
//    }


    @Override
    @Transactional
    public Optional<GuestDto> update(String id, GuestDto guestDto) {
        return guestRepository.findById(id).map(guest -> {
            guest.setFirstName(guestDto.getFirstName());
            guest.setLastName(guestDto.getLastName());
            guest.setMobile(guestDto.getMobile());
            guest.setEmail(guestDto.getEmail());
            return GuestDto.of(guest);
        });
    }

    @Override
    public Page<GuestDto> getAllGuests(String channelId, Pageable pageable) {
        Channel channel = channelRepository.findById(channelId)
                .orElseThrow(() -> new CustomExceptions.ResourceNotFoundException(ErrorCode.NOT_FOUND, "Channel Not Found"));
        return guestRepository.findAllByChannel(channel, pageable).map(GuestDto::of);
    }

    @Override
    public Optional<GuestDto> get(String id) {
        return guestRepository.findById(id).map(GuestDto::of);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GuestDto> autocompleteGuests(String query) {
        if (StringUtils.isBlank(query)) {
            return Collections.emptyList();
        }
        // Filter results directly in JPA query (assuming fields in GuestDto)
        return guestRepository.searchGuestByQuery(query, query, query).stream().map(GuestDto::of).toList();
    }


    @Override
    @Transactional(readOnly = true)
    public Double calculateTotalBill(String guestId) {
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new CustomExceptions.ResourceNotFoundException(ErrorCode.NOT_FOUND, "Guest Not Found"));

        double roomPriceTotal = guest.getBookings().stream()
                .flatMap(booking -> booking.getRooms().stream())
                .mapToDouble(Room::getPricePerNight)
                .sum();

        double orderPriceTotal = orderRepository.findByGuest(guest).stream()
                .flatMap(order -> order.getOrderItems().stream())
                .mapToDouble(orderItem -> orderItem.getMenu().getPrice() * orderItem.getQuantity())
                .sum();

        return roomPriceTotal + orderPriceTotal;
    }

}
