package com.loonds.StaySync.model.dto;

import com.loonds.StaySync.model.entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class OrderDto {
    long id;
    String name;
    List<OrderItemDto> orderItems;

    Instant orderDate;



    public static OrderDto of(Order order){
        OrderDto output = new OrderDto();
        output.setId(order.getId());
//        output.setName(order.getGuest().getFirstName());
        output.setOrderItems(order.getOrderItems().stream().map(OrderItemDto::of).collect(Collectors.toList()));
        output.setOrderDate(order.getOrderDate());
        return output;
    }
}
