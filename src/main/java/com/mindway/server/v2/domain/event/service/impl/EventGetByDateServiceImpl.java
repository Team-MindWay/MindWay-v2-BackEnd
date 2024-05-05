package com.mindway.server.v2.domain.event.service.impl;

import com.mindway.server.v2.domain.event.util.EventConverter;
import com.mindway.server.v2.domain.event.entity.Event;
import com.mindway.server.v2.domain.event.presentation.dto.response.EventGetResponseDto;
import com.mindway.server.v2.domain.event.repository.EventRepository;
import com.mindway.server.v2.domain.event.service.EventGetByDateService;
import com.mindway.server.v2.global.annotation.ServiceWithReadOnlyTransaction;
import lombok.RequiredArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@ServiceWithReadOnlyTransaction
@RequiredArgsConstructor
public class EventGetByDateServiceImpl implements EventGetByDateService {

    private final EventRepository eventRepository;
    private final EventConverter eventConverter;

    public List<EventGetResponseDto> execute(String date) {
        List<Event> allEvents = eventRepository.findAll();
        return validEvent(allEvents, date);
    }

    private List<EventGetResponseDto> validEvent(List<Event> events, String date) {
        try {
            List<Event> validEvents = new ArrayList<>();
            for (Event event: events) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date currentTime = new Date();
                String today = format.format(currentTime);

                Date startDate = format.parse(String.valueOf(event.getStarted_at()));
                Date endDate = format.parse(String.valueOf(event.getEnded_at()));
                Date todate = format.parse(today);

                int compare = todate.compareTo(startDate);
                int compare1 = endDate.compareTo(todate);

                if (compare >= 0 && compare1 >= 0) {
                    validEvents.add(event);
                }

            }
            return validEvents.stream()
                    .map(eventConverter::toDto)
                    .collect(Collectors.toList());
        } catch (ParseException e) {
        }
        return null;
    }
}
