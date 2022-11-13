package com.redcutlery.thingder.api.image.controller;

import com.redcutlery.thingder.api.image.dto.UploadImageResponse;
import com.redcutlery.thingder.api.image.transaction.ImageTransaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Log4j2
@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageController {
    private final ImageTransaction imageTransaction;

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UploadImageResponse uploadImage(@RequestPart("file") MultipartFile file) throws NoSuchAlgorithmException, IOException {
//        return imageTransaction.uploadImage(file);
        return imageTransaction.upload(file);
    }

//    @GetMapping("//**")
//    public ResponseEntity<Resource> getImageFileByPath(HttpServletRequest request) {
//        var fileName = request.getRequestURI().split("/image/")[1];
//        log.info(fileName);
//        return imageTransaction.getImageFileByPath(fileName);
//    }
}
