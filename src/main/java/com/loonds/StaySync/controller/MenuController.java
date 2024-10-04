package com.loonds.StaySync.controller;

import com.loonds.StaySync.model.dto.MenuDto;
import com.loonds.StaySync.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Menu Rest APIs", description = "API for manage Channel menu Operations")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin
public class MenuController {
    private final MenuService menuService;

    @Operation(summary = "Add Menu for Given Channels")
    @PostMapping("/channels/{id}/menus")
    public MenuDto create(@PathVariable String id,
                          @RequestBody MenuDto menuDto){
        return menuService.add(id, menuDto);
    }

    @Operation(summary = "Update given menu item")
    @PutMapping("/menus/{id}")
    public Optional<MenuDto> update(@PathVariable long id,
                                    @RequestBody MenuDto menuDto){
        return menuService.update(id,menuDto);
    }

    @Operation(summary = "Get List Of Menu For Given Channel")
    @GetMapping("/channels/{id}/menus")
    public List<MenuDto> getAllMenuItem(@PathVariable String id){
        return menuService.getMenuList(id);
    }
}
