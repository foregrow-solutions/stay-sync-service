package com.loonds.StaySync.service;

import com.loonds.StaySync.model.dto.MenuDto;

import java.util.List;
import java.util.Optional;

public interface MenuService {
    MenuDto add (String channelId, MenuDto menuDto);
    Optional<MenuDto> update (long menuId, MenuDto menuDto);

    List<MenuDto> getMenuList (String channelId);
}
