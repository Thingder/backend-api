package com.redcutlery.thingder.api.member.transaction;

import com.redcutlery.thingder.api.member.dto.find.MemberResponse;
import com.redcutlery.thingder.api.member.dto.report.ReqReportProfile;
import com.redcutlery.thingder.domain.member.MemberService;
import com.redcutlery.thingder.domain.report.profile.service.ProfileReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberTransaction {
    private final MemberService memberService;
    private final ProfileReportService profileReportService;

    public MemberResponse find(UUID memberUid) {
        return new MemberResponse(memberService.findByUid(memberUid));
    }

    public void delete() {
        var member = memberService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        memberService.delete(member);
    }

    public void report(UUID memberUid, ReqReportProfile reqReportProfile) {
        var member = memberService.findByUid(memberUid);
        profileReportService.report(member, reqReportProfile.getMessage());
    }
}
