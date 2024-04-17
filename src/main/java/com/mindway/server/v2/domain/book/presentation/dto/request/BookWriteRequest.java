package com.mindway.server.v2.domain.book.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BookWriteRequest {
    @NotNull
    private String title;

    @NotNull
    private String plot;
}
