package com.redcutlery.thingder.api.member.dto.find;

import com.redcutlery.thingder.domain.image.dto.SelectImage;
import com.redcutlery.thingder.domain.member.entity.Member;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Data
@ToString
public class MemberResponse {
    private UUID uid;
    private List<SelectImage> images;
    private String nickname;
    private String type;
    private Integer genYear;
    private Integer genMonth;
    private String genCountry;
    private String brand;
    private String tag;
    private String description;
    private String story;

    public MemberResponse(Member member) {
        this.uid = member.getUid();
        this.images = member.getImages().stream()
                .map(SelectImage::new)
                .collect(Collectors.toList());
        this.nickname = member.getNickname();
        this.type = member.getType();
        this.genYear = member.getGenYear();
        this.genMonth = member.getGenMonth();
        this.genCountry = member.getGenCountry();
        this.brand = member.getBrand();
        this.tag = member.getTag();
        this.description = member.getDescription();
        this.story = member.getStory();
    }
}
