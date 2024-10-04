package com.loonds.StaySync.service.impl;

import com.loonds.StaySync.common.exception.CustomExceptions;
import com.loonds.StaySync.common.exception.ErrorCode;
import com.loonds.StaySync.model.dto.RoomDto;
import com.loonds.StaySync.model.entity.Channel;
import com.loonds.StaySync.model.entity.Room;
import com.loonds.StaySync.repository.ChannelRepository;
import com.loonds.StaySync.repository.RoomRepository;
import com.loonds.StaySync.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    private final ChannelRepository channelRepository;

    @Override
    @Transactional
    public RoomDto create(String channelId, RoomDto roomDto) {
        Channel channel = channelRepository.findById(channelId)
                .orElseThrow(() -> new CustomExceptions.ResourceNotFoundException(ErrorCode.NOT_FOUND, "Channel Not Found"));
        Room room = new Room();
        room.setRoomNumber(roomDto.getRoomNumber());
        room.setChannel(channel);
        room.setPricePerNight(roomDto.getPricePerNight());
        Room save = roomRepository.save(room);
        return RoomDto.of(save);
    }

    @Override
    @Transactional
    public Optional<RoomDto> update(long id, RoomDto roomDto) {
        return roomRepository.findById(id).map(room -> {
            room.setRoomNumber(roomDto.getRoomNumber());
            room.setPricePerNight(roomDto.getPricePerNight());
            return RoomDto.of(room);
        });
    }

    @Override
    public Optional<RoomDto> get(long id) {
        return roomRepository.findById(id).map(RoomDto::of);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoomDto> getAllRooms(String channelId) {
        Channel channel = channelRepository.findById(channelId)
                .orElseThrow(() -> new CustomExceptions.ResourceNotFoundException(ErrorCode.NOT_FOUND, "Channel Not Found"));
        return roomRepository.findAllByChannel(channel).stream().map(RoomDto::of).collect(Collectors.toList());
    }

    @Override
    public List<RoomDto> getAvailableRooms(String channelId, LocalDateTime startDate, LocalDateTime endDate) {
        Channel channel = channelRepository.findById(channelId)
                .orElseThrow(() -> new CustomExceptions.ResourceNotFoundException(ErrorCode.NOT_FOUND, "Channel Not Found"));
        return null;
    }
}
