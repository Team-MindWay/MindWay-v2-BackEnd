package com.mindway.server.v2.domain.event.service.impl;

import com.mindway.server.v2.domain.event.entity.Event;
import com.mindway.server.v2.domain.event.entity.Status;
import com.mindway.server.v2.domain.event.repository.EventRepository;
import com.mindway.server.v2.domain.event.service.ChangePendingToNowService;
import com.mindway.server.v2.global.annotation.ServiceWithTransaction;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


@ServiceWithTransaction
@RequiredArgsConstructor
public class ChangePendingToNowServiceImpl implements ChangePendingToNowService {

    private final EventRepository eventRepository;

    public void execute() {
        List<Event> pendingEvents = eventRepository.findByStatus(Status.PENDING);

        for (Event event: pendingEvents) {
            if (Objects.equals(event.getStarted_at(), LocalDate.now())) {
                saveChangeNow(event);
            }
        }
    }

    private void saveChangeNow(Event event) {
        event.changeStatus(Status.NOW);

        eventRepository.save(event);
    }
}
