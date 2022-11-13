package com.redcutlery.thingder.api.auth.dto.resetPassword;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Pattern;

@Data
@ToString
public class ResetPasswordRequest {
    @Pattern(regexp = "(^[\\w-]*\\.[\\w-]*\\.[\\w-]*$)")
    @ApiModelProperty(example = "header.payroad.signature")
    private String emailToken;
    private String password;
}
