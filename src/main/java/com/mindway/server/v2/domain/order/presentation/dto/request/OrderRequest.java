package com.mindway.server.v2.domain.order.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotBlank
    private String book_url;
}
