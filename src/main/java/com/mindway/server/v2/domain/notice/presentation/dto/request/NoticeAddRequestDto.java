package com.mindway.server.v2.domain.notice.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NoticeAddRequestDto {

    @NotNull
    private String title;
    @NotNull
    private String content;

}
