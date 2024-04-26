package com.mindway.server.v2.domain.event.converter;

import com.mindway.server.v2.domain.event.entity.Event;
import com.mindway.server.v2.domain.event.entity.Status;
import com.mindway.server.v2.domain.event.presentation.dto.request.EventUpdateRequestDto;
import com.mindway.server.v2.domain.event.presentation.dto.request.EventWriteRequestDto;
import com.mindway.server.v2.domain.event.presentation.dto.response.EventGetResponseDto;
import com.mindway.server.v2.domain.event.presentation.dto.response.EventInfoResponseDto;
import com.mindway.server.v2.domain.user.entity.User;

public interface EventConverter {
    Event toEntity(EventWriteRequestDto eventWriteRequestDto, User user, String image_url, Status status);

    EventGetResponseDto toDto(Event event);
    EventInfoResponseDto toInfoDto(Event event);
    Event toUpdateEntity(Long eventId, EventUpdateRequestDto eventUpdateRequestDto, User user, String img_url, Status status);
}
