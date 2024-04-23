package com.mindway.server.v2.domain.event.service;

import com.mindway.server.v2.domain.event.entity.Status;
import com.mindway.server.v2.domain.event.presentation.dto.response.EventGetResponseDto;

import java.util.List;

public interface EventGetService {
    List<EventGetResponseDto> execute(Status status);
}
