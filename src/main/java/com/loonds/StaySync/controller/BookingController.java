package com.loonds.StaySync.controller;

import com.loonds.StaySync.model.dto.BookingDto;
import com.loonds.StaySync.model.dto.RoomDto;
import com.loonds.StaySync.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Booking Rest APIs", description = "API for manage Booking Operations")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin
public class BookingController {
    private final BookingService bookingService;

    @Operation(summary = "Get booking details by ID")
    @GetMapping("/bookings/{bookingId}")
    public ResponseEntity<BookingDto> getBookingDetails(@PathVariable long bookingId) {
        return bookingService.getBookingDetails(bookingId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/channels/{channelId}/bookings")
    @Operation(summary = "Get all bookings by channel")
    public Page<BookingDto> getAllBookingsByChannel(@PathVariable String channelId, Pageable pageable) {
        return bookingService.getAllBookingsByChannel(channelId, pageable);
    }

    @GetMapping("/channels/{channelId}/guest/{guestId}/bookings")
    @Operation(summary = "Get all bookings by channel and guest")
    public Page<BookingDto> getAllBookingsByGuestAndChannel(@PathVariable String channelId,
                                                            @PathVariable String guestId,
                                                            @PageableDefault  Pageable pageable) {
        return bookingService.getAllBookingsByGuestAndChannel(channelId,guestId,pageable);
    }

    @PutMapping("/bookings/{bookingId}")
    @Operation(summary = "Update booking by ID")
    public Optional<BookingDto> updateBooking(@PathVariable long bookingId) {
        return bookingService.updateBooking(bookingId);
    }

    @DeleteMapping("/bookings/{bookingId}")
    @Operation(summary = "Cancel booking by ID")
    public void cancelBooking(@PathVariable long bookingId) {
        bookingService.cancelBooking(bookingId);
    }

    @GetMapping("/channels/{channelId}/booked-rooms")
    public List<RoomDto> getBookedRooms(@PathVariable String channelId) {
        return bookingService.getListBookedRooms(channelId);
    }
}
