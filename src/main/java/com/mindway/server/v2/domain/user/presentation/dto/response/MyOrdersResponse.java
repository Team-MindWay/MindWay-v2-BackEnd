package com.mindway.server.v2.domain.user.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyOrdersResponse {
    private String title;
    private String author;
}
