package com.loonds.StaySync.service.impl;

import com.loonds.StaySync.model.dto.NotificationDto;
import com.loonds.StaySync.repository.GuestRepository;
import com.loonds.StaySync.repository.NotificationRepository;
import com.loonds.StaySync.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;
    private final GuestRepository guestRepository;
//    private final UserRepository userRepository;


    @Override
    @Transactional
    public List<NotificationDto> getNotification(String userId, Pageable pageable) {
//        User user = userRepository.getReferenceById(userId);
//        return notificationRepository.findAllByUser(user).stream().map(NotificationDto::of).collect(Collectors.toList());
        return null;
    }

    @Override
    @Transactional
    public NotificationDto createNotification(String userId, String title, String message) {
//        Notification notification = new Notification();
//        notification.setUser(userRepository.getReferenceById(userId));
//        notification.setTitle(title);
//        notification.setMessage(message);
//        notification.setIsOpen(false);
//        notification.setCreatedDate(Instant.now());
//        notification.setModifiedDate(Instant.now());
//        Notification save = notificationRepository.save(notification);
//        return NotificationDto.of(notification);
        return null;
    }

    @Override
    @Transactional
    public Optional<NotificationDto> readNotification(String userId, String id, Boolean isOpen) {
//        return notificationRepository.findById(id).map(notification -> {
//            notification.setIsOpen(isOpen);
//            return NotificationDto.of(notification);
//        });
        return null;
    }

    @Override
    @Transactional
    public List<NotificationDto> readAllNotification(String userId, Boolean isOpen) {
//        return notificationRepository.findAllByUser(userRepository.getReferenceById(userId)).stream().map(notification -> {
//            notification.setIsOpen(isOpen);
//            return NotificationDto.of(notification);
//        }).collect(Collectors.toList());
        return null;
    }
}
