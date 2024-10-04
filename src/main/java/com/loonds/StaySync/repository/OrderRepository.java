package com.loonds.StaySync.repository;

import com.loonds.StaySync.model.entity.Channel;
import com.loonds.StaySync.model.entity.Guest;
import com.loonds.StaySync.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByGuest(Guest guest);

    @Query("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.orderItems oi LEFT JOIN FETCH oi.menu WHERE o.channel = :channel")
    List<Order> findByChannel(@Param("channel") Channel channel);
}
