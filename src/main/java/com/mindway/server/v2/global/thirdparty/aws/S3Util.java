package com.mindway.server.v2.global.thirdparty.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.mindway.server.v2.global.exception.ErrorCode;
import com.mindway.server.v2.global.exception.MindWayException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class S3Util {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;

    public String imageUpload(MultipartFile image) {
        try {
            List<String> list = List.of("jpg", "jpeg", "png", "gif");

            String[] splitFile = image.getOriginalFilename().split("\\.");

            if (splitFile.length < 2)
                throw new MindWayException(ErrorCode.FILE_EXTENSION_INVALID);

            String extension = splitFile[1].toLowerCase();

            if (list.stream().noneMatch(it -> it.equals(extension)))
                throw new MindWayException(ErrorCode.FILE_EXTENSION_INVALID);

            return upload(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String upload(MultipartFile file) throws IOException {
        String profileName = bucket + "/" + UUID.randomUUID() + file.getOriginalFilename();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getInputStream().available());
        amazonS3.putObject(bucket, profileName, file.getInputStream(), metadata);
        return amazonS3.getUrl(bucket, profileName).toString();
    }
}
