package com.mindway.server.v2.domain.event.presentation;

import com.mindway.server.v2.domain.event.entity.Status;
import com.mindway.server.v2.domain.event.presentation.dto.request.EventWriteRequestDto;
import com.mindway.server.v2.domain.event.presentation.dto.response.EventGetResponseDto;
import com.mindway.server.v2.domain.event.service.EventGetService;
import com.mindway.server.v2.domain.event.service.EventWriteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/event")
public class EventController {

    private final EventWriteService eventWriteService;
    private final EventGetService eventGetService;

    @PostMapping
    public ResponseEntity<Void> writeEvent(
            @Valid @RequestPart("dto") EventWriteRequestDto eventWriteRequestDto,
            @RequestPart(required = false) MultipartFile image
    ) {
        eventWriteService.execute(eventWriteRequestDto, image);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<EventGetResponseDto>> getEvent(@RequestParam(required = false) Status status) {
        List<EventGetResponseDto> responses = eventGetService.execute(status);
        return ResponseEntity.ok(responses);
    }



}
