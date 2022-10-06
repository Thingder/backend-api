package com.redcutlery.thingder.domain.image.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class InsertImage {
    private String src;
    private String srcSet;
}
