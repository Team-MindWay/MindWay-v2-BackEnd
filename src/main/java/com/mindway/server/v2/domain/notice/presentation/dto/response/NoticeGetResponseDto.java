package com.mindway.server.v2.domain.notice.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NoticeGetResponseDto {
    private String title;
    private String content;
}
