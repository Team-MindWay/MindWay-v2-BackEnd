package com.mindway.server.v2.domain.image.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ImageUploadResponseDto {
    private String img_url;
}
