package com.redcutlery.thingder.api.matching.dto.pick;

import com.redcutlery.thingder.domain.MemberRelation.entity.MemberRelation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
@AllArgsConstructor
public class PickResponse {
    private UUID chatUid;
    private boolean isMatch = false;
    public PickResponse(MemberRelation memberRelation) {
        if (memberRelation.getRelationType().equals(MemberRelation.RelationType.LIKE)) {
            var chat = memberRelation.getChatRoom();
            this.chatUid = chat.getUid();

            var targetRelationExist = chat.getMemberRelations().stream()
                    .filter(r -> r.getTarget().equals(memberRelation.getMember()) && r.getRelationType().equals(MemberRelation.RelationType.LIKE))
                    .findFirst();

            if (targetRelationExist.isPresent())
                this.isMatch = true;
        }
    }
}
