package com.mindway.server.v2.domain.event.service.impl;

import com.mindway.server.v2.domain.event.entity.Event;
import com.mindway.server.v2.domain.event.entity.Status;
import com.mindway.server.v2.domain.event.repository.EventRepository;
import com.mindway.server.v2.domain.event.service.ChangeEventStatusService;
import com.mindway.server.v2.global.annotation.ServiceWithTransaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@ServiceWithTransaction
@RequiredArgsConstructor
@Slf4j
public class ChangeEventStatusServiceImpl implements ChangeEventStatusService {

    private final EventRepository eventRepository;

    public void execute() {
        log.info("===================이벤트 스케줄러 시작===================");
        List<Event> pendingEvents = eventRepository.findByStatus(Status.PENDING);
        List<Event> nowEvents = eventRepository.findByStatus(Status.NOW);

        saveChangeNow(pendingEvents);
        saveChangePast(nowEvents);
        log.info("===================이벤트 스케줄러 끝===================");
    }

    private void saveChangePast(List<Event> nowEvents) {
        for (Event event: nowEvents) {
            if (Objects.equals(event.getStarted_at(), LocalDate.now())) {
                event.changeStatus(Status.PAST);
                eventRepository.save(event);
                log.info("EventId: {}, status: NOW -> PAST", event.getId());
            }
        }
        log.info("===================EventNowToPast End===================");
    }

    private void saveChangeNow(List<Event> pendingEvents) {
        try {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date now = formatter.parse(String.valueOf(LocalDate.now()));

            for (Event event: pendingEvents) {
                Date endDate = formatter.parse(String.valueOf(event.getEnded_at()));

                if (now.after(endDate)) {
                    event.changeStatus(Status.NOW);
                    eventRepository.save(event);
                    log.info("EventId: {}, status: PENDING -> NOW", event.getId());
                }
            }
            log.info("===================EventPendingToNow End===================");
        } catch (ParseException e) {}
    }
}
