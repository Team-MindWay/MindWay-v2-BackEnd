package com.mindway.server.v2.domain.rec.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RecInfoResponse {
    private Long id;
    private String title;
    private String content;
    private String author;
}
