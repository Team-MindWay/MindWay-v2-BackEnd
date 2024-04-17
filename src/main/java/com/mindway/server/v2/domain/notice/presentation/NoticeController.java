package com.mindway.server.v2.domain.notice.presentation;

import com.mindway.server.v2.domain.notice.presentation.dto.request.NoticeAddRequestDto;
import com.mindway.server.v2.domain.notice.presentation.dto.response.NoticeGetResponseDto;
import com.mindway.server.v2.domain.notice.service.NoticeAddService;
import com.mindway.server.v2.domain.notice.service.NoticeGetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/notice")
public class NoticeController {

    private final NoticeAddService noticeAddService;
    private final NoticeGetService noticeGetService;

    @PostMapping
    public ResponseEntity<Void> addNotice(@Valid @RequestBody NoticeAddRequestDto noticeAddRequestDto) {
        noticeAddService.execute(noticeAddRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<NoticeGetResponseDto> getNotice() {
        NoticeGetResponseDto response = noticeGetService.execute();
        return ResponseEntity.ok(response);
    }

}
