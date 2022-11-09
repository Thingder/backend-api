package com.redcutlery.thingder.domain.chat.entity;

import com.redcutlery.thingder.domain.MemberRelation.entity.MemberRelation;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom {
    @Id
    @GeneratedValue
    @Column(name = "uid", columnDefinition = "BINARY(16)")
    private UUID uid;
    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL)
    private List<MemberRelation> memberRelations = new ArrayList<>();
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<ChatMessage> messages = new ArrayList<>();

    public void addMemberRelation(MemberRelation memberRelation) {
        this.memberRelations.add(memberRelation);
        memberRelation.setChatRoom(this);
    }

    public void addMessage(ChatMessage chatMessage) {
        this.messages.add(chatMessage);
        chatMessage.setRoom(this);
    }
}
