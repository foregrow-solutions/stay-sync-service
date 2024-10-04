package com.loonds.StaySync.service;
import com.loonds.StaySync.model.dto.NotificationDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface NotificationService {
    List<NotificationDto> getNotification(String userId, Pageable pageable);

    NotificationDto createNotification(String userId, String title, String message);

    Optional<NotificationDto> readNotification(String userId, String id, Boolean isOpen);

    List<NotificationDto> readAllNotification(String userId, Boolean isOpen);

}
