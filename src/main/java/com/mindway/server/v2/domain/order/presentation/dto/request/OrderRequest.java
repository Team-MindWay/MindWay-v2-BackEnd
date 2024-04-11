package com.mindway.server.v2.domain.order.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^https://www\\.yes24\\.com$", message = "YES24 링크를 입력해주세요")
    private String book_url;
}
