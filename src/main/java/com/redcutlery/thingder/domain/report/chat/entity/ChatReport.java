package com.redcutlery.thingder.domain.report.chat.entity;

import com.redcutlery.thingder.domain.chat.entity.ChatRoom;
import com.redcutlery.thingder.domain.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class ChatReport {
    @Id
    @GeneratedValue
    @Column(name = "uid", columnDefinition = "BINARY(16)")
    private UUID uid;
    @Column(columnDefinition = "TEXT")
    private String message;
    @ManyToOne
    @JoinColumn(name = "chat_room_uid")
    private ChatRoom chatRoom;
    @ManyToOne
    @JoinColumn(name = "subject_uid")
    private Member subject;

    public enum Status {
        BAN,
        DISMISS
    }

    private Status status;
}
