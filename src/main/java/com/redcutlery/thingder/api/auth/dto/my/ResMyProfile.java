package com.redcutlery.thingder.api.auth.dto.my;

import com.redcutlery.thingder.domain.member.entity.Member;
import com.redcutlery.thingder.domain.member.param.MemberRole;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class ResMyProfile {
    private UUID uid;
    private Set<MemberRole> roles;

    public ResMyProfile(Member member) {
        this.uid = member.getUid();
        this.roles = member.getRoleSet();
    }
}
