package com.loonds.StaySync.repository;

import com.loonds.StaySync.model.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, String> {
//    List<Notification> findAllByUser(User user);
}
