package com.redcutlery.thingder.api.matching.dto.pick;

import com.redcutlery.thingder.domain.memberMember.param.MemberRelation;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
public class PickRequest {
    private UUID uid;
    private MemberRelation relation;
}
