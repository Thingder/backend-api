package com.redcutlery.thingder.domain.image.dto;

import com.redcutlery.thingder.domain.memberImage.entity.MemberImage;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
public class SelectImage {
    private UUID uid;
    private String src;
    private String srcSet;

    public SelectImage(MemberImage memberImage) {
        var image = memberImage.getImage();
        this.uid = image.getUid();
        this.src = image.getSrc();
        this.srcSet = image.getSrcSet();
    }
}
