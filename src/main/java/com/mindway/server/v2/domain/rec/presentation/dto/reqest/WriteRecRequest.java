package com.mindway.server.v2.domain.rec.presentation.dto.reqest;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class WriteRecRequest {
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String author;
}
