package com.loonds.StaySync.event;

import com.loonds.StaySync.service.BookingService;
import com.loonds.StaySync.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class EventConsumer {
    private final NotificationService notificationService;

    private final BookingService bookingService;

    @Async
    @TransactionalEventListener
    public void handle(NotificationEvent event) {
        log.info("Handling Notification Event : {}", event);
        notificationService.createNotification(event.userId(), event.title(), event.message());
    }

    @Async
    @TransactionalEventListener
    public void handle(BookingEvent event) {
        log.info("Handling Booking Event : {}", event);
        bookingService.bookRoom(event.channelId(), event.guestId(), event.roomIds(), event.inOutRecord());
    }

}
