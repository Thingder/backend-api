package com.redcutlery.thingder.domain.report.profile.entity;

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
public class ProfileReport {
    @Id
    @GeneratedValue
    @Column(name = "uid", columnDefinition = "BINARY(16)")
    private UUID uid;
    @Column(columnDefinition = "TEXT")
    private String message;
    @ManyToOne
    @JoinColumn(name = "member_uid")
    private Member member;

    public enum Status {
        BAN,
        DISMISS
    }

    private Status status;
}
