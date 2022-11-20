package com.redcutlery.thingder.domain.chat.entity;

import com.redcutlery.thingder.domain.member.entity.Member;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@EntityListeners(AuditingEntityListener.class)
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
    @CreatedDate
    private LocalDateTime sendAt;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
