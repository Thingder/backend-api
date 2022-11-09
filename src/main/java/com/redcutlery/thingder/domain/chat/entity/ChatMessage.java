package com.redcutlery.thingder.domain.chat.entity;

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
public class ChatMessage {
    public enum MessageType {
        ENTER, TALK
    }

    @Id
    @GeneratedValue
    @Column(name = "uid", columnDefinition = "BINARY(16)")
    private UUID uid;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_uid")
    private ChatRoom room;
    private MessageType type;
    @ManyToOne
    private Member sender;
    @Column(columnDefinition = "TEXT")
    private String message;

    public ChatMessage(Member member, ChatRoom room, String message) {
        this.sender = member;
        this.room = room;
        this.message = message;
    }

    public void setChatRoom(ChatRoom room) {
        this.room = room;
        room.getMessages().add(this);
    }
}
