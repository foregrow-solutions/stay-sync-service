package com.loonds.StaySync.service;

import com.loonds.StaySync.controller.payload.GuestCheckInPayload;
import com.loonds.StaySync.controller.payload.GuestPayload;
import com.loonds.StaySync.model.dto.GuestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface GuestService {
    GuestDto create(String channelId, GuestPayload payload);
    Optional<GuestDto> update(String id, GuestDto guestDto);
    Page<GuestDto> getAllGuests(String channelId, Pageable pageable);
    Optional<GuestDto> get(String id);

    List<GuestDto> autocompleteGuests(String query);
    Double calculateTotalBill(String guestId);
}
