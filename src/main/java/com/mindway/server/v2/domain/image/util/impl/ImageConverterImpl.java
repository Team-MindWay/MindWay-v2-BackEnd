package com.mindway.server.v2.domain.image.util.impl;

import com.mindway.server.v2.domain.image.presentation.dto.response.ImageUploadResponseDto;
import com.mindway.server.v2.domain.image.util.ImageConverter;
import org.springframework.stereotype.Component;

@Component
public class ImageConverterImpl implements ImageConverter {

    public ImageUploadResponseDto toDto(String img_url) {
        return ImageUploadResponseDto.builder()
                .img_url(img_url)
                .build();
    }
}
