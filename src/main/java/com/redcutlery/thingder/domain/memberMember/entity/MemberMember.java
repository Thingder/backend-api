package com.redcutlery.thingder.domain.memberMember.entity;

import com.redcutlery.thingder.domain.member.entity.Member;
import com.redcutlery.thingder.domain.memberMember.param.MemberRelation;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class MemberMember {
    @Id
    @GeneratedValue
    @Column(name = "uid", columnDefinition = "BINARY(16)")
    private UUID uid;
    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "picker")
    private Member picker;
    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject")
    private Member subject;
    @NonNull
    @Enumerated(EnumType.STRING)
    @ElementCollection
    private Set<MemberRelation> relations = new HashSet<>();

    public MemberMember(@NonNull Member picker, @NonNull Member subject, MemberRelation relation) {
        this.picker = picker;
        this.subject = subject;
        this.relations = Set.of(relation);
    }
}
