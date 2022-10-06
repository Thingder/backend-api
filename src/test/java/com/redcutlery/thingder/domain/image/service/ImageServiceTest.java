package com.redcutlery.thingder.domain.image.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ImageServiceTest {
    @Autowired
    ImageService imageService;

    @Test
    void findImageFileTest() {
        imageService.getImageFileByPath("4a67e7a25c8463b2de37c608121aa6509257976660fa095909a918660420adb0/스크린샷 2022-09-23 오전 8.30.40.png");
    }
}