package com.loonds.StaySync.repository;

import com.loonds.StaySync.model.entity.Channel;
import com.loonds.StaySync.model.entity.Guest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GuestRepository extends JpaRepository<Guest, String> {
    Page<Guest> findAllByChannel(Channel channel, Pageable pageable);

    @Query("SELECT g FROM Guest g WHERE g.firstName LIKE :firstName OR g.email LIKE :email OR g.mobile LIKE :mobile ORDER BY g.firstName")
    List<Guest> searchGuestByQuery(@Param("firstName") String firstName, @Param("email") String email, @Param("mobile") String mobile);
}
