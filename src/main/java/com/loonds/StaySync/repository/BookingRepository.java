package com.loonds.StaySync.repository;

import com.loonds.StaySync.model.entity.Booking;
import com.loonds.StaySync.model.entity.Channel;
import com.loonds.StaySync.model.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface BookingRepository extends JpaRepository<Booking, Long> {

    Page<Booking> findAllByChannel(Channel channel, Pageable pageable);

    @Query("SELECT DISTINCT r FROM Room r JOIN r.booking b WHERE b.channel.id = :channelId")
    List<Room> findBookedRoomsByChannel(@Param("channelId") String channelId);
//    @Query("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.orderItems oi LEFT JOIN FETCH oi.menu WHERE o.channel = :channel")
//    List<Order> findByChanne  l(@Param("channel") Channel channel);
}
