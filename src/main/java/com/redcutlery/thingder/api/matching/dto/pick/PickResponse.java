package com.redcutlery.thingder.api.matching.dto.pick;

import com.redcutlery.thingder.api.member.dto.find.MemberResponse;
import com.redcutlery.thingder.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class PickResponse {
    private boolean isMatch;
    private MemberResponse member;

    public PickResponse(Member member) {
        if (member == null) {
            this.isMatch = false;
        } else {
            this.isMatch = true;
            this.member = new MemberResponse(member);
        }
    }
}
