package com.redcutlery.thingder.domain.image.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@ToString
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ImageInfo {
    @Column(columnDefinition = "TEXT")
    private String url;
    private Long size;
}
