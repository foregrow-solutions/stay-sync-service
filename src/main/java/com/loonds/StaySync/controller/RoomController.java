package com.loonds.StaySync.controller;

import com.loonds.StaySync.model.dto.RoomDto;
import com.loonds.StaySync.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Tag(name = "Room Rest APIs", description = "API for manage Room Operations")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RoomController {
    private final RoomService roomService;

    @PostMapping("/channels/{id}/rooms")
    @Operation(summary = "Create a new Room Given Channel")
    public RoomDto createRoom(@PathVariable String id,
                              @RequestBody RoomDto payload) {
        return roomService.create(id, payload);
    }

    @PutMapping("/rooms/{id}")
    @Operation(summary = "Update Given Room Details")
    public Optional<RoomDto> updateRoom(@PathVariable long id,
                                        @RequestBody RoomDto roomDto){
        return roomService.update(id,roomDto);
    }

    @GetMapping("/channels/{id}/rooms")
    @Operation(summary = "Get list of All rooms in given Channels")
    public List<RoomDto> getAllRooms(@PathVariable String id){
        return roomService.getAllRooms(id);
    }


}
