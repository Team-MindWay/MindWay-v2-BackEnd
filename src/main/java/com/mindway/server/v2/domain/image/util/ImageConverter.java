package com.mindway.server.v2.domain.image.util;

import com.mindway.server.v2.domain.image.presentation.dto.response.ImageUploadResponseDto;

public interface ImageConverter {
    ImageUploadResponseDto toDto(String img_url);
}
