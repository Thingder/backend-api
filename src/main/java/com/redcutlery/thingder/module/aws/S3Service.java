package com.redcutlery.thingder.module.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Log4j2
@Service
@RequiredArgsConstructor
public class S3Service {
    private final AmazonS3 s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public String uploadBytes(byte[] bytes, String key, String contentType) throws IOException {
        var metadata = new ObjectMetadata();
        metadata.setContentType(contentType);
        metadata.setContentLength(bytes.length);
        var byteArrayInputStream = new ByteArrayInputStream(bytes);
        s3Client.putObject(bucketName, key, byteArrayInputStream, metadata);
        return s3Client.getUrl(bucketName, key).toString();
    }
}
