package com.redcutlery.thingder.domain.member.entity;

import com.google.common.collect.Streams;
import com.redcutlery.thingder.api.auth.dto.register.RegisterRequest;
import com.redcutlery.thingder.domain.MemberRelation.entity.MemberRelation;
import com.redcutlery.thingder.domain.image.dto.InsertImage;
import com.redcutlery.thingder.domain.image.dto.SelectImage;
import com.redcutlery.thingder.domain.member.param.MemberRole;
import com.redcutlery.thingder.domain.memberImage.entity.MemberImage;
import lombok.*;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "uid", columnDefinition = "BINARY(16)")
    private UUID uid;
    @NonNull
    private String email;
    @NonNull
    private String password;
    private String phone;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<MemberRole> roleSet = new HashSet<>();

    public enum Status {
        BANNED,
        CERTIFIED
    }

    private Status status;

    @ToString.Exclude
    @OrderBy("idx asc")
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberImage> images = new ArrayList<>();

    private String nickname;
    private String type;
    private Integer genYear;
    private Integer genMonth;
    private String genCountry;
    private String brand;
    private String tag;
    private String description;
    private String story;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    @ToString.Exclude
    List<MemberRelation> picks = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "target")
    @ToString.Exclude
    List<MemberRelation> subjects = new ArrayList<>();

    public Member(RegisterRequest registerRequest) {
        this.email = registerRequest.getEmail();
        this.password = registerRequest.getPassword();
        this.roleSet = Set.of(MemberRole.USER);

        insertImages(registerRequest.getImages());

        this.nickname = registerRequest.getNickname();
        this.type = registerRequest.getType();
        this.genYear = registerRequest.getGenYear();
        this.genMonth = registerRequest.getGenMonth();
        this.genCountry = registerRequest.getGenCountry();
        this.brand = registerRequest.getBrand();
        this.tag = registerRequest.getTag();
        this.description = registerRequest.getDescription();
        this.story = registerRequest.getStory();
    }

    public MemberImage insertImage(InsertImage image, Long index) {
        return new MemberImage(this, image, index);
    }

    public void insertImages(List<InsertImage> images) {
        this.images = Streams.mapWithIndex(images.stream(), this::insertImage)
                .collect(Collectors.toList());
    }

    public List<SelectImage> selectImages() {
        return this.images.stream()
                .map(SelectImage::new)
                .collect(Collectors.toList());
    }

    public void addMemberRole(MemberRole memberRole) {
        roleSet.add(memberRole);
    }
}
