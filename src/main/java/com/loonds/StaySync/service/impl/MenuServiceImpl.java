package com.loonds.StaySync.service.impl;

import com.loonds.StaySync.model.dto.MenuDto;
import com.loonds.StaySync.model.entity.Channel;
import com.loonds.StaySync.model.entity.Menu;
import com.loonds.StaySync.repository.ChannelRepository;
import com.loonds.StaySync.repository.MenuRepository;
import com.loonds.StaySync.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {
    private final MenuRepository itemRepository;
    private final ChannelRepository channelRepository;
    @Override
    @Transactional
    public MenuDto add(String channelId, MenuDto menuDto) {
        Optional<Channel> channel = channelRepository.findById(channelId);
        if(channel.isPresent()){
            Menu item = new Menu();
            item.setChannel(channel.get());
            item.setItemName(menuDto.getItemName());
            item.setDescription(menuDto.getDescription());
            item.setPrice(menuDto.getPrice());
            Menu save = itemRepository.save(item);
            return MenuDto.of(save);
        }else {
            return null;
        }
    }

    @Override
    @Transactional
    public Optional<MenuDto> update(long menuId, MenuDto menuDto) {
        return itemRepository.findById(menuId).map(item -> {
            item.setItemName(menuDto.getItemName());
            item.setDescription(menuDto.getDescription());
            item.setPrice(menuDto.getPrice());
            return MenuDto.of(item);
        });
    }

    @Override
    @Transactional(readOnly = true)
    public List<MenuDto> getMenuList(String channelId) {
//        Optional<Channel> byId = channelRepository.findById(channelId);
//        return itemRepository.findAllByChannel(byId).stream().collect(Collectors.toList());

        Optional<Channel> channel = channelRepository.findById(channelId);
        return channel.map(value -> itemRepository.findAllByChannel(value)
                .stream().map(MenuDto::of)
                .collect(Collectors.toList())).orElse(Collections.emptyList());
    }
}
