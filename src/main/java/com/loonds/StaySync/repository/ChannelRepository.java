package com.loonds.StaySync.repository;

import com.loonds.StaySync.model.entity.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<Channel, String> {
}
