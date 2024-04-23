package com.mindway.server.v2.domain.event.service;

import com.mindway.server.v2.domain.event.entity.Status;
import com.mindway.server.v2.domain.event.presentation.dto.response.EventGetsResponseDto;

import java.util.List;

public interface EventGetsService {
    List<EventGetsResponseDto> execute(Status status);
}
