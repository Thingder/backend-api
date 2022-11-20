package com.redcutlery.thingder.api.admin.dto.findAllChat;

import com.redcutlery.thingder.domain.image.dto.SelectImage;
import com.redcutlery.thingder.domain.member.entity.Member;
import lombok.Data;

import java.util.UUID;

@Data
public class ResSubject {
    private UUID uid;
    private String nickname;
    private SelectImage image;

    public ResSubject(Member subject) {
        this.uid = subject.getUid();
        this.nickname = subject.getNickname();
        this.image = new SelectImage(subject.getImages().get(0));
    }
}
