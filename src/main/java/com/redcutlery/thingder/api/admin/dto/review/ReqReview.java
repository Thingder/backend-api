package com.redcutlery.thingder.api.admin.dto.review;

import com.redcutlery.thingder.domain.member.entity.Member;
import lombok.Data;

@Data
public class ReqReview {
    private Member.Status status;
}
