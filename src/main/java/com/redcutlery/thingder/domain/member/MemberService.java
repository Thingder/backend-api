package com.redcutlery.thingder.domain.member;

import com.redcutlery.thingder.api.auth.dto.register.RegisterRequest;
import com.redcutlery.thingder.api.matching.dto.pick.PickRequest;
import com.redcutlery.thingder.domain.member.entity.Member;
import com.redcutlery.thingder.domain.member.repository.MemberRepository;
import com.redcutlery.thingder.domain.memberMember.entity.MemberMember;
import com.redcutlery.thingder.domain.memberMember.param.MemberRelation;
import com.redcutlery.thingder.domain.memberMember.service.MemberMemberService;
import com.redcutlery.thingder.exception.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMemberService memberMemberService;

    public Member findByUid(UUID uid) {
        return memberRepository.findById(uid)
                .orElseThrow(() -> new BaseException.NotFound("존재하지 않는 멤버입니다."));
    }

    public boolean existByEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

    public Member register(RegisterRequest registerRequest, String phone) {
        var member = new Member(registerRequest);
        member.setPhone(phone);
        return memberRepository.save(member);
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(() -> new BaseException.NotFound("존재하지 않는 멤버입니다."));
    }

    public List<Member> findList(Member member) {
        var members = memberRepository.findAll();

        members = members.stream()
                .filter(m -> !m.getPicks().contains(member))
                .collect(Collectors.toList());

        return members;
    }

    public Member pick(PickRequest pickRequest, Member member) {
        var subject = findByUid(pickRequest.getUid());

        var memberMember = new MemberMember(member, subject, pickRequest.getRelation());
        member.getPicks().add(memberMember);

        if (pickRequest.getRelation().equals(MemberRelation.LIKE) &&
                memberMemberService.checkByPickerAndSubject(subject, member))
            return subject;
        return null;
    }
}
