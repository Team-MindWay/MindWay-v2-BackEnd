package com.mindway.server.v2.domain.image.presentation;

import com.mindway.server.v2.domain.image.presentation.dto.response.ImageUploadResponseDto;
import com.mindway.server.v2.domain.image.service.ImageUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageUploadService imageUploadService;

    @PostMapping
    public ResponseEntity<ImageUploadResponseDto> uploadImage(@RequestPart MultipartFile image) {
        ImageUploadResponseDto response = imageUploadService.execute(image);
        return ResponseEntity.ok(response);
    }
}
