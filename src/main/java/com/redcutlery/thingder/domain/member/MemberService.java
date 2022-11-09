package com.redcutlery.thingder.domain.member;

import com.redcutlery.thingder.api.auth.dto.register.RegisterRequest;
import com.redcutlery.thingder.api.matching.dto.pick.PickRequest;
import com.redcutlery.thingder.domain.MemberRelation.entity.MemberRelation;
import com.redcutlery.thingder.domain.MemberRelation.repository.MemberRelationRepository;
import com.redcutlery.thingder.domain.MemberRelation.serivce.MemberRelationService;
import com.redcutlery.thingder.domain.chat.entity.ChatRoom;
import com.redcutlery.thingder.domain.member.entity.Member;
import com.redcutlery.thingder.domain.member.repository.MemberRepository;
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
    private final MemberRelationService memberRelationService;
    private final MemberRelationRepository memberRelationRepository;

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
                .filter(m -> !m.equals(member))
                .collect(Collectors.toList());

        return members;
    }

    public MemberRelation pick(PickRequest pickRequest, Member member) {
        var target = findByUid(pickRequest.getUid());

        var memberRelation = new MemberRelation(member, target, pickRequest.getRelation());

        if (memberRelation.getRelationType().equals(MemberRelation.RelationType.LIKE)) {
            var targetRelationExist = memberRelationService.findByMemberAndTarget(target, member);

            if (targetRelationExist.isPresent()) {
                var targetRelation = targetRelationExist.get();
                if (targetRelation.getRelationType().equals(MemberRelation.RelationType.LIKE)) {
                    var chat = targetRelation.getChatRoom();
                    chat.addMemberRelation(memberRelation);
                }
            } else {
                var chat = new ChatRoom();
                chat.addMemberRelation(memberRelation);
            }
        }
        return memberRelationRepository.save(memberRelation);
    }

    public void delete(Member member) {
        memberRepository.delete(member);
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member setStatus(UUID memberUid, Member.Status status) {
        var member = findByUid(memberUid);
        member.setStatus(status);
        return memberRepository.save(member);
    }
}
