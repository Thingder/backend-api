package com.redcutlery.thingder.domain.image.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ImageFile {
    @Id
    @GeneratedValue
    @Column(name = "uid", columnDefinition = "BINARY(16)")
    private UUID uid;
    private String sha256;
    @Column(columnDefinition = "TEXT")
    private String originalName;
    @Embedded
    private ImageInfo imageInfo;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "imageFile")
    @ToString.Exclude
    private List<ResizedImageFile> resizedImageFiles = new ArrayList<>();

    @Builder
    public ImageFile(String originalName,
                     String url,
                     String sha256,
                     Long size) {
        this.originalName = originalName;
        this.sha256 = sha256;
        this.imageInfo = new ImageInfo(url, size);
    }

    public void addResizedImageFile(ResizedImageFile resizedImageFile) {
        this.resizedImageFiles.add(resizedImageFile);
        if (resizedImageFile.getImageFile() != this) {
            resizedImageFile.setImageFile(this);
        }
    }

    public String getSrc() {
        return this.imageInfo.getUrl();
    }

    public String getSrcSet() {
        return this.getResizedImageFiles().stream()
                .map(resizedImageFile ->
                        resizedImageFile.getImageInfo().getUrl() + " " +
                                resizedImageFile.getWidth().toString() + "w")
                .collect(Collectors.joining(", "));
    }
}
