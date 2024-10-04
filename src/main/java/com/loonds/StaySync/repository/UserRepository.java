package com.loonds.StaySync.repository;

import com.loonds.StaySync.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
