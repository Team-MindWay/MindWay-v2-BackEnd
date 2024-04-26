package com.mindway.server.v2.domain.event.presentation;

import com.mindway.server.v2.domain.event.entity.Status;
import com.mindway.server.v2.domain.event.presentation.dto.request.EventUpdateRequestDto;
import com.mindway.server.v2.domain.event.presentation.dto.request.EventWriteRequestDto;
import com.mindway.server.v2.domain.event.presentation.dto.response.EventGetResponseDto;
import com.mindway.server.v2.domain.event.presentation.dto.response.EventInfoResponseDto;
import com.mindway.server.v2.domain.event.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/event")
public class EventController {

    private final EventWriteService eventWriteService;
    private final EventGetService eventGetService;
    private final EventInfoService eventInfoService;
    private final EventDeleteService eventDeleteService;
    private final EventGetByDateService eventGetByDateService;
    private final EventUpdateService eventUpdateService;

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

    @GetMapping("/{event_id}")
    public ResponseEntity<EventInfoResponseDto> getInfoEvent(@PathVariable("event_id") Long id) {
        EventInfoResponseDto response = eventInfoService.execute(id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{event_id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable("event_id") Long id) {
        eventDeleteService.execute(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/date")
    public ResponseEntity<List<EventGetResponseDto>> getEventByDate(@RequestParam String date) throws ParseException {
        List<EventGetResponseDto> responses = eventGetByDateService.execute(date);
        return ResponseEntity.ok(responses);
    }

    @PatchMapping("/{event_id}")
    public ResponseEntity<Void> updateEvent(
        @PathVariable("event_id") Long eventId,
        @Valid @RequestPart("dto") EventUpdateRequestDto eventUpdateRequestDto,
        @RequestPart(required = false) MultipartFile image
    ) {
        eventUpdateService.execute(eventId, eventUpdateRequestDto, image);
        return ResponseEntity.status(HttpStatus.RESET_CONTENT).build();
    }

}
