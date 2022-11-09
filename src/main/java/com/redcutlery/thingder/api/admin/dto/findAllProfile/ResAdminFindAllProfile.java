package com.redcutlery.thingder.api.admin.dto.findAllProfile;

import com.redcutlery.thingder.domain.report.profile.entity.ProfileReport;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ResAdminFindAllProfile {
    private List<ResAdminFindAllProfileElement> profiles;

    public ResAdminFindAllProfile(List<ProfileReport> profileReports) {
        this.profiles = profileReports.stream()
                .map(ResAdminFindAllProfileElement::new)
                .collect(Collectors.toList());
    }
}
