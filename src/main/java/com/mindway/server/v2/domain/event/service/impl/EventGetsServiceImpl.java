package com.mindway.server.v2.domain.event.service.impl;

import com.mindway.server.v2.domain.event.converter.EventConverter;
import com.mindway.server.v2.domain.event.entity.Status;
import com.mindway.server.v2.domain.event.presentation.dto.response.EventGetsResponseDto;
import com.mindway.server.v2.domain.event.repository.EventRepository;
import com.mindway.server.v2.domain.event.service.EventGetsService;
import com.mindway.server.v2.global.annotation.ServiceWithReadOnlyTransaction;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@ServiceWithReadOnlyTransaction
@RequiredArgsConstructor
public class EventGetsServiceImpl implements EventGetsService {

    private final EventRepository eventRepository;
    private final EventConverter eventConverter;

    public List<EventGetsResponseDto> execute(Status status) {

        return eventRepository.findByStatus(status).stream()
                .map(eventConverter::toDto)
                .collect(Collectors.toList());


    }
}