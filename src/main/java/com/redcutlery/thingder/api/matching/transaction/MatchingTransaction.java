package com.redcutlery.thingder.api.matching.transaction;

import com.redcutlery.thingder.api.matching.dto.findList.MemberListResponse;
import com.redcutlery.thingder.api.matching.dto.pick.PickRequest;
import com.redcutlery.thingder.api.matching.dto.pick.PickResponse;
import com.redcutlery.thingder.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class MatchingTransaction {
    private final MemberService memberService;

    public MemberListResponse findList() {
        var member = memberService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());

        return new MemberListResponse(memberService.findList(member));
    }

    public PickResponse pick(PickRequest pickRequest) {
        var member = memberService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        return new PickResponse(memberService.pick(pickRequest, member));
    }
}
