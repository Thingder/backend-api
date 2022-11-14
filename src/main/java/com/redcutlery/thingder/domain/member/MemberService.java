package com.redcutlery.thingder.domain.member;

import com.redcutlery.thingder.api.auth.dto.login.LoginRequest;
import com.redcutlery.thingder.api.auth.dto.register.RegisterRequest;
import com.redcutlery.thingder.api.auth.dto.udpateMy.ReqUpdateMy;
import com.redcutlery.thingder.api.matching.dto.pick.PickRequest;
import com.redcutlery.thingder.domain.MemberRelation.entity.MemberRelation;
import com.redcutlery.thingder.domain.MemberRelation.repository.MemberRelationRepository;
import com.redcutlery.thingder.domain.MemberRelation.serivce.MemberRelationService;
import com.redcutlery.thingder.domain.chat.entity.ChatRoom;
import com.redcutlery.thingder.domain.member.entity.Member;
import com.redcutlery.thingder.domain.member.param.MemberRole;
import com.redcutlery.thingder.domain.member.repository.MemberRepository;
import com.redcutlery.thingder.exception.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
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
        member.setRoleSet(Set.of(MemberRole.USER));
        return memberRepository.save(member);
    }

    public void updateMy(ReqUpdateMy reqUpdateMy) {
        var member = findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        member.update(reqUpdateMy);
        log.info(member.getImages().get(0).getImage());
        member = memberRepository.save(member);
        log.info(member.getImages().get(0).getImage());
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

        var memberRelation = memberRelationRepository.findByMemberAndTarget(member, target)
                .orElseGet(() -> memberRelationRepository.save(new MemberRelation(member, target, pickRequest.getRelation())));

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

    public Member createAdmin(LoginRequest loginRequest) {
        var member = new Member();
        member.setEmail(loginRequest.getEmail());
        member.setPassword(loginRequest.getPassword());
        member.setRoleSet(Set.of(MemberRole.ADMIN));
        return memberRepository.save(member);
    }

    public Member setPassword(Member member, String password) {
        member.setPassword(password);
        return memberRepository.save(member);
    }
}
