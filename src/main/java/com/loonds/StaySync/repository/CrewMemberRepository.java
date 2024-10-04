package com.loonds.StaySync.repository;

import com.loonds.StaySync.model.entity.CrewMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrewMemberRepository extends JpaRepository<CrewMember, Long> {
}
