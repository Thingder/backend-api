package com.redcutlery.thingder.domain.image.entity;

import com.redcutlery.thingder.domain.image.dto.InsertImage;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue
    @Column(name = "uid", columnDefinition = "BINARY(16)")
    private UUID uid;
    @Column(columnDefinition = "TEXT")
    private String src;
    @Column(columnDefinition = "TEXT")
    private String srcSet;

    public Image(InsertImage image) {
        this.src = image.getSrc();
        this.srcSet = image.getSrcSet();
    }
}
