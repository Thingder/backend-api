package com.redcutlery.thingder.api.image.transaction;

import com.redcutlery.thingder.api.image.dto.UploadImageResponse;
import com.redcutlery.thingder.domain.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Log
@Service
@RequiredArgsConstructor
public class ImageTransaction {
    private final ImageService imageService;

    public UploadImageResponse uploadImage(MultipartFile file) throws NoSuchAlgorithmException, IOException {
        return new UploadImageResponse(imageService.uploadImage(file));
    }

    public ResponseEntity<Resource> getImageFileByPath(String fileName) {
        return imageService.getImageFileByPath(fileName);
    }

    public UploadImageResponse upload(MultipartFile file) throws NoSuchAlgorithmException, IOException {
        return new UploadImageResponse(imageService.upload(file));
    }
}
