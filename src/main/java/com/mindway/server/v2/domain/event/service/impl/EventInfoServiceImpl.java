package com.mindway.server.v2.domain.event.service.impl;

import com.mindway.server.v2.domain.event.converter.EventConverter;
import com.mindway.server.v2.domain.event.entity.Event;
import com.mindway.server.v2.domain.event.exception.NotFoundEventException;
import com.mindway.server.v2.domain.event.presentation.dto.response.EventInfoResponseDto;
import com.mindway.server.v2.domain.event.repository.EventRepository;
import com.mindway.server.v2.domain.event.service.EventInfoService;
import com.mindway.server.v2.global.annotation.ServiceWithReadOnlyTransaction;
import lombok.RequiredArgsConstructor;

@ServiceWithReadOnlyTransaction
@RequiredArgsConstructor
public class EventInfoServiceImpl implements EventInfoService {

    private final EventRepository eventRepository;
    private final EventConverter eventConverter;

    public EventInfoResponseDto execute(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(NotFoundEventException::new);

        return eventConverter.toInfoDto(event);
    }
}
