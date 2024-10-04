package com.loonds.StaySync.controller;

import com.loonds.StaySync.service.NotificationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@Tag(name = "Notification APIs", description = "API for manage Notification Operations")
@Slf4j
@RestController
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;


}
