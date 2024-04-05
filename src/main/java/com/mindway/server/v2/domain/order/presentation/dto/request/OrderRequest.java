package com.mindway.server.v2.domain.order.presentation.dto.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderRequest {
    private String title;
    private String author;
    private String book_url;
}
