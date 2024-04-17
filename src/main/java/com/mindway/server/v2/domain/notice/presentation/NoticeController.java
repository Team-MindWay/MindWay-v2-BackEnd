package com.mindway.server.v2.domain.notice.presentation;

import com.mindway.server.v2.domain.notice.presentation.dto.request.NoticeAddRequestDto;
import com.mindway.server.v2.domain.notice.service.NoticeAddService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/notice")
public class NoticeController {

    private final NoticeAddService noticeAddService;

    @PostMapping
    public ResponseEntity<Void> addNotice(@Valid @RequestBody NoticeAddRequestDto noticeAddRequestDto) {
        noticeAddService.execute(noticeAddRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
