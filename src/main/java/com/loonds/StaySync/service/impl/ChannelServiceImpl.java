package com.loonds.StaySync.service.impl;

import com.loonds.StaySync.controller.payload.ChannelPayload;
import com.loonds.StaySync.model.dto.ChannelDto;
import com.loonds.StaySync.model.entity.Channel;
import com.loonds.StaySync.repository.ChannelRepository;
import com.loonds.StaySync.service.ChannelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChannelServiceImpl implements ChannelService {
    private final ChannelRepository channelRepository;

    @Override
    @Transactional
    public ChannelDto create(String userId, ChannelPayload payload) {
        Channel channel = new Channel();
        channel.setName(payload.name());
        channel.setEmail(payload.email());
        channel.setMobile(payload.mobile());
        Channel save = channelRepository.save(channel);
        return ChannelDto.of(save);
    }

    @Override
    @Transactional
    public Optional<ChannelDto> update(String channelId, ChannelDto channelDto) {
        return channelRepository.findById(channelId).map(channel -> {
            channel.setName(channelDto.getName());
            channel.setEmail(channelDto.getEmail());
            channel.setMobile(channelDto.getMobile());
            return ChannelDto.of(channel);
        });
    }

    @Override
    public Optional<ChannelDto> get(String channelId) {
        return channelRepository.findById(channelId).map(ChannelDto::of);
    }

    @Override
    public Page<ChannelDto> getAllChannel(String query, Pageable pageable) {
        return channelRepository.findAll(pageable).map(ChannelDto::of);
    }
}
