package com.loonds.StaySync.model.dto;

import com.loonds.StaySync.model.entity.Menu;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuDto {
    long id;
    String itemName;
    String description;
    Double price;

    public static MenuDto of (Menu item){
        MenuDto output = new MenuDto();
        output.setId(item.getId());
        output.setItemName(item.getItemName());
        output.setDescription(item.getDescription());
        output.setPrice(item.getPrice());
        return output;
    }
}
