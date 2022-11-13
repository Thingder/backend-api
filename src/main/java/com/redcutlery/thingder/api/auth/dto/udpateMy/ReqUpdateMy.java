package com.redcutlery.thingder.api.auth.dto.udpateMy;

import com.redcutlery.thingder.domain.image.dto.InsertImage;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class ReqUpdateMy {
    private List<InsertImage> images;
    private String nickname;
    private String type;
    private Integer genYear;
    private Integer genMonth;
    private String genCountry;
    private String brand;
    private String tag;
    private String description;
    private String story;
}
