package com.redcutlery.thingder.domain.report.profile.service;

import com.redcutlery.thingder.domain.member.entity.Member;
import com.redcutlery.thingder.domain.report.profile.entity.ProfileReport;
import com.redcutlery.thingder.domain.report.profile.repository.ProfileReportRepository;
import com.redcutlery.thingder.exception.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProfileReportService {
    private final ProfileReportRepository profileReportRepository;

    public List<ProfileReport> findAll() {
        return profileReportRepository.findAll();
    }

    public ProfileReport findByUid(UUID profileReportUid) {
        return profileReportRepository
                .findById(profileReportUid)
                .orElseThrow(() -> new BaseException.NotFound("존재하지 않는 프로필 신고내역입니다."));
    }

    public ProfileReport setStatus(UUID profileReportUid, ProfileReport.Status status) {
        var profileReport = findByUid(profileReportUid);
        profileReport.setStatus(status);
        return profileReportRepository.save(profileReport);
    }

    public ProfileReport report(Member member, String message) {
        var profileReport = new ProfileReport();
        profileReport.setMember(member);
        profileReport.setMessage(message);
        return profileReportRepository.save(profileReport);
    }
}
