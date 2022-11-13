package com.redcutlery.thingder.api.image.dto;

import com.redcutlery.thingder.domain.image.entity.ImageFile;
import lombok.Data;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Data
@Log4j2
@ToString
public class UploadImageResponse {
    private String src;
    private String srcSet;

    public UploadImageResponse(ImageFile imageFile) {
        this.src = imageFile.getSrc();
        this.srcSet = imageFile.getSrcSet();
    }
}
