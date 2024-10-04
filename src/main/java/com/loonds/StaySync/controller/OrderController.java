package com.loonds.StaySync.controller;

import com.loonds.StaySync.controller.payload.OrderItemPayload;
import com.loonds.StaySync.model.dto.OrderDto;
import com.loonds.StaySync.model.entity.Room;
import com.loonds.StaySync.repository.BookingRepository;
import com.loonds.StaySync.repository.RoomRepository;
import com.loonds.StaySync.service.GuestService;
import com.loonds.StaySync.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "Order Rest APIs", description = "API for manage Guest Order Operations")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin
public class OrderController {
    private final OrderService orderService;
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;

    @PostMapping("/guests/{id}/orders")
    @Operation(summary = "Create new Order for given Guest")
    public OrderDto createOrder(@PathVariable String id,
                                @RequestBody OrderItemPayload[] payload){
        return orderService.placeOrder(id,payload);
    }

    //Todo : Need Reflector code need clean approach get guest form booked room id
    @PostMapping("/rooms/{id}/orders")
    @Operation(summary = "Create new Order for given Guest by Booked Room")
    public OrderDto createOrder(@PathVariable long id,
                                @RequestBody OrderItemPayload[] payload){
        // Retrieve the room by ID
        Room room = roomRepository.findById(id).orElse(null);
        if (room != null && room.getBooking() != null && room.getBooking().getGuest() != null) {
            // If room is booked and has a guest associated with it, proceed with placing the order
            return orderService.placeOrder(room.getBooking().getGuest().getId(), payload);
        } else {
            // Handle the case where room is not booked or no guest associated
            return null; // Or throw an exception
        }
//        return orderService.placeOrder(id,payload);
    }

    @PutMapping("/orders/{id}")
    @Operation(summary = "Update Given Order Details")
    public Optional<OrderDto> updateOrder(@PathVariable long id,
                                          @RequestBody OrderDto orderDto){
        return orderService.updateOrder(id, orderDto);
    }

    @GetMapping("/guests/{id}/orders")
    @Operation(summary = "Get list of all orders for Given Guests")
    public Page<OrderDto> getGuestAllOrders(@PathVariable String id,
                                       @PageableDefault Pageable pageable){
        return orderService.getGuestAllOrder(id, pageable);
    }

    @GetMapping("/channels/{id}/orders")
    @Operation(summary = "Get list of all orders for Given Channel")
    public Page<OrderDto> getAllOrders(@PathVariable String id,
                                       @PageableDefault Pageable pageable){
        return orderService.getAllOrder(id, pageable);
    }
}
