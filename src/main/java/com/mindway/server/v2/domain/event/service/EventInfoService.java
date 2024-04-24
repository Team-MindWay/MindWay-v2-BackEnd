package com.mindway.server.v2.domain.event.service;

import com.mindway.server.v2.domain.event.presentation.dto.response.EventInfoResponseDto;

public interface EventInfoService {
    EventInfoResponseDto execute(Long id);
}
