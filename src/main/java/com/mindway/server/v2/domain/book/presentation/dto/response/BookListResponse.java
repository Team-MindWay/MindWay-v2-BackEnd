package com.mindway.server.v2.domain.book.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class BookListResponse {
    private Long id;
    private String title;
    private String plot;
    private String date;
}


