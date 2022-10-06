package com.redcutlery.thingder.api.matching.dto.pick;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class PickResponse {
    private boolean isMatch;
}
