package com.mindway.server.v2.domain.event.service.impl;

import com.mindway.server.v2.domain.event.entity.Event;
import com.mindway.server.v2.domain.event.entity.Status;
import com.mindway.server.v2.domain.event.repository.EventRepository;
import com.mindway.server.v2.domain.event.service.ChangeNowToPastService;
import com.mindway.server.v2.global.annotation.ServiceWithTransaction;
import lombok.RequiredArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@ServiceWithTransaction
@RequiredArgsConstructor
public class ChangeNowToPastServiceImpl implements ChangeNowToPastService {

    private final EventRepository eventRepository;

    public void execute() {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            Date now = formatter.parse(String.valueOf(LocalDate.now()));

            List<Event> events = eventRepository.findByStatus(Status.NOW);

            for (Event event: events) {
                Date endDate = formatter.parse(String.valueOf(event.getEnded_at()));

                if (now.after(endDate)) {
                    saveChangePast(event);
                }
            }
        } catch (ParseException e) {
        }


    }

    private void saveChangePast(Event event) {
        event.changeStatus(Status.PAST);

        eventRepository.save(event);
    }
}
