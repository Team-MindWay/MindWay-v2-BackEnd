package com.mindway.server.v2.domain.event.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EventInfoResponseDto {
    private String title;
    private String content;
    private String img_url;
    private String started_at;
    private String ended_at;
}
