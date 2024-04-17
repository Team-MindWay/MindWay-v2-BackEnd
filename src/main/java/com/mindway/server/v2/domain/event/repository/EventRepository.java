package com.mindway.server.v2.domain.event.repository;

import com.mindway.server.v2.domain.event.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
