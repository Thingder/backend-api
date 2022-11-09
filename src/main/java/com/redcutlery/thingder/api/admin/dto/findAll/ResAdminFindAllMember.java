package com.redcutlery.thingder.api.admin.dto.findAll;

import com.redcutlery.thingder.domain.member.entity.Member;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ResAdminFindAllMember {
    private List<ResAdminFindAllMemberElement> members;

    public ResAdminFindAllMember(List<Member> members) {
        this.members = members.stream()
                .map(ResAdminFindAllMemberElement::new)
                .collect(Collectors.toList());
    }
}
