package com.redcutlery.thingder.domain.MemberRelation.entity;

import com.redcutlery.thingder.domain.chat.entity.ChatRoom;
import com.redcutlery.thingder.domain.member.entity.Member;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class MemberRelation {
    public enum RelationType {
        LIKE, DISLIKE, BLOCK
    }

    @Id
    @GeneratedValue
    @Column(name = "uid", columnDefinition = "BINARY(16)")
    private UUID uid;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "member_uid")
    private Member member;
    @NonNull
    @ManyToOne
    @JoinColumn(name = "target_uid")
    private Member target;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "chat_uid")
    private ChatRoom chatRoom;
    @NonNull
    @Enumerated(EnumType.STRING)
    private RelationType relationType;
}
