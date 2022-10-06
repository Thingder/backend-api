package com.redcutlery.thingder.api.matching.dto.findList;

import com.redcutlery.thingder.domain.member.entity.Member;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Data
@ToString
public class MemberListResponse {
    private List<MemberElementResponse> members;

    public MemberListResponse(List<Member> members) {
        this.members = members.stream()
                .map(MemberElementResponse::new)
                .collect(Collectors.toList());
    }
}
