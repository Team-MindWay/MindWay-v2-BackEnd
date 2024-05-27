package com.mindway.server.v2.domain.order.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrdersResponse {
    private Long id;
    private String title;
    private String author;
    private String book_url;
}
