package com.redcutlery.thingder.domain.image.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResizedImageFile {
    @Id
    @GeneratedValue
    @Column(name = "uid", columnDefinition = "BINARY(16)")
    private UUID uid;
    private Integer width;
    @Embedded
    private ImageInfo imageInfo;

    @ManyToOne
    @JoinColumn(name = "image_file_uid")
    private ImageFile imageFile;

    @Builder
    public ResizedImageFile(Integer width,
                            String url,
                            Long size) {
        this.width = width;
        this.imageInfo = new ImageInfo(url, size);
    }
}
