package com.mindway.server.v2.domain.event.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    private String img_url = "";
}
