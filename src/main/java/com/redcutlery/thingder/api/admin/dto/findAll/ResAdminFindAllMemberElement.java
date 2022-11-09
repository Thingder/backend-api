package com.redcutlery.thingder.api.admin.dto.findAll;

import com.redcutlery.thingder.domain.image.dto.SelectImage;
import com.redcutlery.thingder.domain.member.entity.Member;
import lombok.Data;

import java.util.UUID;

@Data
public class ResAdminFindAllMemberElement {
    private UUID uid;
    private SelectImage image;
    private String nickname;
    private String type;
    private Integer genYear;
    private Integer genMonth;
    private String genCountry;
    private String brand;

    public ResAdminFindAllMemberElement(Member member) {
        this.uid = member.getUid();
        this.image = new SelectImage(member.getImages().get(0));
        this.nickname = member.getNickname();
        this.type = member.getType();
        this.genYear = member.getGenYear();
        this.genMonth = member.getGenMonth();
        this.genCountry = member.getGenCountry();
        this.brand = member.getBrand();
    }
}
