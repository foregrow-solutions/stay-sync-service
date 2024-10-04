package com.loonds.StaySync.service;

import com.loonds.StaySync.model.dto.RoomDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RoomService {

    RoomDto create(String channelId, RoomDto roomDto);

    Optional<RoomDto> update(long id, RoomDto roomDto);

    Optional<RoomDto> get(long id);

    List<RoomDto> getAllRooms(String channelId);
    List<RoomDto> getAvailableRooms(String channelId, LocalDateTime startDate, LocalDateTime endDate);
}
