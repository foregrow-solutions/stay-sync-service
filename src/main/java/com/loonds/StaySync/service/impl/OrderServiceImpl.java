package com.loonds.StaySync.service.impl;

import com.loonds.StaySync.common.exception.CustomExceptions;
import com.loonds.StaySync.common.exception.ErrorCode;
import com.loonds.StaySync.controller.payload.OrderItemPayload;
import com.loonds.StaySync.model.dto.OrderDto;
import com.loonds.StaySync.model.entity.*;
import com.loonds.StaySync.repository.*;
import com.loonds.StaySync.service.ChannelService;
import com.loonds.StaySync.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final MenuRepository menuRepository;
    private final OrderRepository orderRepository;
    private final GuestRepository guestRepository;
    private final OrderItemRepository orderItemRepository;

    private final ChannelRepository channelRepository;

    @Override
    @Transactional
    public OrderDto placeOrder(String guestId, OrderItemPayload[] orderDto) {
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() -> new CustomExceptions.ResourceNotFoundException(ErrorCode.NOT_FOUND, "Guest Not Found"));

        Order order = new Order();
        order.setGuest(guest);
        order.setChannel(guest.getChannel());
        order.setOrderDate(Instant.now());

        Set<OrderItem> orderItems = Arrays.stream(orderDto).distinct()
                .map(itemPayload -> createOrderItem(order,itemPayload.menuItem(), itemPayload.quantity()))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        order.setOrderItems(orderItems);

        Order savedOrder = orderRepository.save(order);

        return OrderDto.of(savedOrder);
    }

    //Todo : Check Menu item is valid and also check menu item is available
    private OrderItem createOrderItem(Order order,long menuItemId, int quantity) {
        Optional<Menu> menuOptional = menuRepository.findById(menuItemId);

        return menuOptional.map(menu -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setMenu(menu);
            orderItem.setQuantity(quantity);
            orderItem.setOrder(order);
            return orderItemRepository.save(orderItem);
        }).orElse(null);
    }


    @Override
    public Optional<OrderDto> updateOrder(long id, OrderDto orderDto) {
        return Optional.empty();
    }

    @Override
    public Page<OrderDto> getGuestAllOrder(String guestId, Pageable pageable) {
        Optional<Guest> guest = guestRepository.findById(guestId);
        if (guest.isPresent()) {
            Channel channel = guest.get().getChannel();
            List<Order> orders = orderRepository.findByChannel(channel);
            log.info("orders is size : ", orders.size());
            orders.stream().map(order -> {
                log.info("order detail", order.getOrderItems().size());
                return order;
            });

            return new PageImpl<>(orders.stream().map(OrderDto::of).collect(Collectors.toList()), pageable, orders.size());
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OrderDto> getAllOrder(String channelId, Pageable pageable) {
        Optional<Channel> channel = channelRepository.findById(channelId);
        if (channel.isPresent()) {
            List<Order> orders = orderRepository.findByChannel(channel.get());
            log.info("orders is size : ", orders.size());
            orders.stream().map(order -> {
                log.info("order detail", order.getOrderItems().size());
                return order;
            });

            return new PageImpl<>(orders.stream().map(OrderDto::of).collect(Collectors.toList()), pageable, orders.size());
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderDto> getGuestAllOrder(String guestId) {
        Optional<Guest> guest = guestRepository.findById(guestId);
        if (guest.isPresent()){
            List<Order> byGuest = orderRepository.findByGuest(guest.get());
            return byGuest.stream().map(OrderDto::of).collect(Collectors.toList());
        }else {
            return null;
        }
    }

}
