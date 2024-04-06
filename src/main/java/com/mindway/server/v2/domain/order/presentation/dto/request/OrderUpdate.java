package com.mindway.server.v2.domain.order.presentation.dto.request;

import lombok.Getter;

@Getter
public class OrderUpdate {
    private String title;
    private String author;
    private String book_url;
}
