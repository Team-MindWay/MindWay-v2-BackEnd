package com.mindway.server.v2.domain.event.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EventUpdateRequestDto {
    @NotNull
    private String title;
    @NotNull
    private String content;
    @NotNull
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "created_at의 형식이 yyyy-MM-dd가 아닙니다.")
    private String created_at;
    @NotNull
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "ended_at의 형식이 yyyy-MM-dd가 아닙니다.")
    private String ended_at;
}
