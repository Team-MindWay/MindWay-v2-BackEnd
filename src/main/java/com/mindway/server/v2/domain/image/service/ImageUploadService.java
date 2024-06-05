package com.mindway.server.v2.domain.image.service;

import com.mindway.server.v2.domain.image.presentation.dto.response.ImageUploadResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface ImageUploadService {
    ImageUploadResponseDto execute(MultipartFile image);
}
