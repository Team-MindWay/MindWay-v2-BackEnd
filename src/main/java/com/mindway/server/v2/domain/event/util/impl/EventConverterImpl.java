package com.mindway.server.v2.domain.event.util.impl;

import com.mindway.server.v2.domain.event.util.EventConverter;
import com.mindway.server.v2.domain.event.entity.Event;
import com.mindway.server.v2.domain.event.entity.Status;
import com.mindway.server.v2.domain.event.presentation.dto.request.EventUpdateRequestDto;
import com.mindway.server.v2.domain.event.presentation.dto.request.EventWriteRequestDto;
import com.mindway.server.v2.domain.event.presentation.dto.response.EventGetResponseDto;
import com.mindway.server.v2.domain.event.presentation.dto.response.EventInfoResponseDto;
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

    public EventGetResponseDto toDto(Event event) {
        return EventGetResponseDto.builder()
                .id(event.getId())
                .title(event.getTitle())
                .content(event.getContent())
                .img_url(event.getImg_url())
                .started_at(String.valueOf(event.getStarted_at()))
                .ended_at(String.valueOf(event.getEnded_at()))
                .build();
    }

    public EventInfoResponseDto toInfoDto(Event event) {
        return EventInfoResponseDto.builder()
                .title(event.getTitle())
                .content(event.getContent())
                .img_url(event.getImg_url())
                .started_at(String.valueOf(event.getStarted_at()))
                .ended_at(String.valueOf(event.getEnded_at()))
                .build();
    }

    public Event toUpdateEntity(Long eventId, EventUpdateRequestDto eventUpdateRequestDto, User user, String img_url, Status status) {
        return Event.builder()
                .id(eventId)
                .title(eventUpdateRequestDto.getTitle())
                .content(eventUpdateRequestDto.getContent())
                .started_at(LocalDate.parse(eventUpdateRequestDto.getCreated_at()))
                .ended_at(LocalDate.parse(eventUpdateRequestDto.getEnded_at()))
                .img_url(img_url)
                .status(status)
                .user(user)
                .build();
    }
}
