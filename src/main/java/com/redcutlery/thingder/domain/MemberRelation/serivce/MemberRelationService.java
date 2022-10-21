package com.redcutlery.thingder.domain.MemberRelation.serivce;

import com.redcutlery.thingder.domain.MemberRelation.entity.MemberRelation;
import com.redcutlery.thingder.domain.MemberRelation.repository.MemberRelationRepository;
import com.redcutlery.thingder.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberRelationService {
    private final MemberRelationRepository memberRelationRepository;

    public Optional<MemberRelation> findByMemberAndTarget(Member target, Member member) {
        return memberRelationRepository.findByMemberAndTarget(target, member);
    }
}
