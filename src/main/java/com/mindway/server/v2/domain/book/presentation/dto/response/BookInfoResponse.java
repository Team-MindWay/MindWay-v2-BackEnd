package com.mindway.server.v2.domain.book.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookInfoResponse {
    private String title;
    private String plot;
}
