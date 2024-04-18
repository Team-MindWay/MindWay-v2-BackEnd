package com.mindway.server.v2.domain.event.service;

import com.mindway.server.v2.domain.event.presentation.dto.request.EventWriteRequestDto;
import org.springframework.web.multipart.MultipartFile;


public interface EventWriteService {
    void execute(EventWriteRequestDto eventWriteRequestDto, MultipartFile image);
}
