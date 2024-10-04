package com.loonds.StaySync.service;

import com.loonds.StaySync.controller.payload.ChannelPayload;
import com.loonds.StaySync.model.dto.ChannelDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ChannelService {
    ChannelDto create(String userId, ChannelPayload payload);
    Optional<ChannelDto> update(String channelId, ChannelDto channelDto);
    Optional<ChannelDto> get(String channelId);

    Page<ChannelDto> getAllChannel(String query, Pageable pageable);

}
