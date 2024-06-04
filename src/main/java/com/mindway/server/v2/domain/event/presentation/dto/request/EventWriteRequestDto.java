package com.mindway.server.v2.domain.event.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class EventWriteRequestDto {
    @NotNull
    private String title;
    @NotNull
    private String content;
    @NotNull
    private String started_at;
    @NotNull
    private String ended_at;
}
