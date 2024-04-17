package com.mindway.server.v2.domain.event.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class EventWriteRequestDto {
    private String title;
    private String content;
    private String created_at;
    private String ended_at;
}
