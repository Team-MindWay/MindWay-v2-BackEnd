package com.mindway.server.v2.domain.image.service.impl;

import com.mindway.server.v2.domain.image.presentation.dto.response.ImageUploadResponseDto;
import com.mindway.server.v2.domain.image.service.ImageUploadService;
import com.mindway.server.v2.domain.image.util.ImageConverter;
import com.mindway.server.v2.global.annotation.ServiceWithTransaction;
import com.mindway.server.v2.global.thirdparty.aws.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@ServiceWithTransaction
@RequiredArgsConstructor
public class ImageUploadServiceImpl implements ImageUploadService {

    private final S3Util s3Util;
    private final ImageConverter imageConverter;

    public ImageUploadResponseDto execute(MultipartFile image) {
        String img_url = s3Util.imageUpload(image);
        return imageConverter.toDto(img_url);
    }
}
