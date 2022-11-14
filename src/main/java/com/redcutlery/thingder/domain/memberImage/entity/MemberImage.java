package com.redcutlery.thingder.domain.memberImage.entity;

import com.redcutlery.thingder.domain.image.dto.InsertImage;
import com.redcutlery.thingder.domain.image.entity.Image;
import com.redcutlery.thingder.domain.member.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "member_image")
public class MemberImage {
    @Id
    @GeneratedValue
    @Column(name = "uid", columnDefinition = "BINARY(16)")
    private UUID uid;
    @NonNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "member_uid")
    @ToString.Exclude
    private Member member;
    @NonNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "image_uid")
    @ToString.Exclude
    private Image image;

    private Long idx;

    public MemberImage(@NonNull Member member, InsertImage image, Long index) {
        this.idx = index;
        this.member = member;
        this.image = new Image(image);
    }
}
