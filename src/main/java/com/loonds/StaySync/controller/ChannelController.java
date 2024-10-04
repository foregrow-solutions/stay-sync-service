package com.loonds.StaySync.controller;

import com.loonds.StaySync.controller.payload.ChannelPayload;
import com.loonds.StaySync.controller.payload.GuestPayload;
import com.loonds.StaySync.model.dto.ChannelDto;
import com.loonds.StaySync.model.dto.GuestDto;
import com.loonds.StaySync.model.entity.Channel;
import com.loonds.StaySync.service.ChannelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "Channel Rest APIs", description = "API for manage Channel Operations")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/channels")
public class ChannelController {
    private final ChannelService channelService;

    @PostMapping
    @Operation(summary = "Create a new Channel")
    public ChannelDto createChannel(@RequestBody ChannelPayload payload) {
        return channelService.create("1",payload);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Given Channel Details")
    public Optional<ChannelDto> updateChannel(@PathVariable String id,
                                             @RequestBody ChannelDto payload) {
        return channelService.update(id,payload);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Details of  Given Channel")
    public Optional<ChannelDto> getChannelDetails(@PathVariable String id) {
        return channelService.get(id);
    }

    @GetMapping
    @Operation(summary = "Get List of Channels")
    public Page<ChannelDto> getAllChannelList(@PageableDefault Pageable pageable){
        return channelService.getAllChannel("", pageable);
    }
}
