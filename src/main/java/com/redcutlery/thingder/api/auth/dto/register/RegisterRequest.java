package com.redcutlery.thingder.api.auth.dto.register;

import com.redcutlery.thingder.domain.image.dto.InsertImage;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@ToString
public class RegisterRequest {
    @Pattern(regexp = "(^[\\w-]*\\.[\\w-]*\\.[\\w-]*$)")
    @ApiModelProperty(example = "header.payroad.signature")
    private String pinToken;
    private String email;
    private String password;
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
