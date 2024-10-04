package com.loonds.StaySync.controller;

import com.loonds.StaySync.service.MediaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Tag(name = "Media Rest APIs", description = "API for manage Media Operations")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/settings/upload")
public class MediaController {
    private final MediaService mediaService;

    @Operation(summary = "Upload Media Images, Banner")
    @PostMapping
    public String uploadMedia(@RequestParam("file") MultipartFile file) throws IOException {
        return mediaService.upload("", file);
    }

}
