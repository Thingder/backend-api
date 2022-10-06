package com.redcutlery.thingder.domain.memberMember.service;

import com.redcutlery.thingder.domain.member.entity.Member;
import com.redcutlery.thingder.domain.memberMember.param.MemberRelation;
import com.redcutlery.thingder.domain.memberMember.repository.MemberMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberMemberService {
    private final MemberMemberRepository memberMemberRepository;

    public boolean checkByPickerAndSubject(Member picker, Member subject) {
        var exist = memberMemberRepository.findByPickerAndSubject(picker, subject);
        if (exist.isEmpty())
            return false;
        return exist.get().getRelations().contains(MemberRelation.LIKE);
    }
}
