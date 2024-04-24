package com.mindway.server.v2.domain.event.service;

import com.mindway.server.v2.domain.event.presentation.dto.response.EventGetResponseDto;

import java.text.ParseException;
import java.util.List;

public interface EventGetByDateService {
    List<EventGetResponseDto> execute(String date) throws ParseException;
}
