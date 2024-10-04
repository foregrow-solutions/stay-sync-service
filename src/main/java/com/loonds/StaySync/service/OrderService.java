package com.loonds.StaySync.service;

import com.loonds.StaySync.controller.payload.OrderItemPayload;
import com.loonds.StaySync.model.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    OrderDto placeOrder(String guestId, OrderItemPayload[] payload);

    Optional<OrderDto> updateOrder(long id, OrderDto orderDto);

    Page<OrderDto> getGuestAllOrder(String guestId, Pageable pageable);
    Page<OrderDto> getAllOrder(String channelId, Pageable pageable);
    List<OrderDto> getGuestAllOrder(String guestId);
}
