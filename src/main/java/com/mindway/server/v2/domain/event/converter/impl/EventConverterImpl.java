package com.mindway.server.v2.domain.event.converter.impl;

import com.mindway.server.v2.domain.event.converter.EventConverter;
import com.mindway.server.v2.domain.event.entity.Event;
import com.mindway.server.v2.domain.event.entity.Status;
import com.mindway.server.v2.domain.event.presentation.dto.request.EventWriteRequestDto;
import com.mindway.server.v2.domain.event.presentation.dto.response.EventGetsResponseDto;
import com.mindway.server.v2.domain.user.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class EventConverterImpl implements EventConverter {
    public Event toEntity(EventWriteRequestDto eventWriteRequestDto, User user, String image_url, Status status) {
        return Event.builder()
                .title(eventWriteRequestDto.getTitle())
                .content(eventWriteRequestDto.getContent())
                .started_at(LocalDate.parse(eventWriteRequestDto.getCreated_at()))
                .ended_at(LocalDate.parse(eventWriteRequestDto.getEnded_at()))
                .img_url(image_url)
                .status(status)
                .user(user)
                .build();
    }

    public EventGetsResponseDto toDto(Event event) {
        return EventGetsResponseDto.builder()
                .id(event.getId())
                .title(event.getTitle())
                .img_url(event.getImg_url())
                .started_at(String.valueOf(event.getStarted_at()))
                .ended_at(String.valueOf(event.getEnded_at()))
                .build();
    }
}
