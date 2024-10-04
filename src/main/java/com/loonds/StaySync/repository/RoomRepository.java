package com.loonds.StaySync.repository;

import com.loonds.StaySync.model.entity.Channel;
import com.loonds.StaySync.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    List<Room> findAllByChannel(Channel channel);
}
