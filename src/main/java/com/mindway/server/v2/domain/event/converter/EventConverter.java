package com.mindway.server.v2.domain.event.converter;

import com.mindway.server.v2.domain.event.entity.Event;
import com.mindway.server.v2.domain.event.presentation.dto.request.EventWriteRequestDto;
import com.mindway.server.v2.domain.user.entity.User;

public interface EventConverter {
    Event toEntity(EventWriteRequestDto eventWriteRequestDto, User user, String image_url);
}
