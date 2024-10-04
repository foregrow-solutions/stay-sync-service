package com.loonds.StaySync.model.dto;

import com.loonds.StaySync.model.entity.OrderItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDto {
    private String itemName;
    private int quantity;

    public static OrderItemDto of(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setItemName(orderItem.getMenu().getItemName());
        orderItemDto.setQuantity(orderItem.getQuantity());
        return orderItemDto;
    }
}
