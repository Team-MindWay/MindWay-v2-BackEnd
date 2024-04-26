package com.mindway.server.v2.domain.event.service;

import com.mindway.server.v2.domain.event.presentation.dto.request.EventUpdateRequestDto;
import org.springframework.web.multipart.MultipartFile;

public interface EventUpdateService {
    void execute(Long eventId, EventUpdateRequestDto eventUpdateRequestDto, MultipartFile image);
}
