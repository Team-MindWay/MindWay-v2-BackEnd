package com.mindway.server.v2.domain.event.repository;

import com.mindway.server.v2.domain.event.entity.Event;
import com.mindway.server.v2.domain.event.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByStatus(Status status);
    boolean existsEventById(Long id);
}
