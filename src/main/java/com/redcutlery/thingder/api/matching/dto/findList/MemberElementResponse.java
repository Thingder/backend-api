package com.redcutlery.thingder.api.matching.dto.findList;

import com.redcutlery.thingder.domain.member.entity.Member;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
public class MemberElementResponse {
    private UUID uid;
    private String nickname;
    private String type;
    private Integer genYear;
    private Integer genMonth;
    private String genCountry;
    private String brand;

    public MemberElementResponse(Member member) {
        this.uid = member.getUid();
        this.nickname = member.getNickname();
        this.type = member.getType();
        this.genYear = member.getGenYear();
        this.genMonth = member.getGenMonth();
        this.genCountry = member.getGenCountry();
        this.brand = member.getBrand();
    }
}
