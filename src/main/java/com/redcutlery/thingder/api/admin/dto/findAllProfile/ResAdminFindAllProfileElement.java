package com.redcutlery.thingder.api.admin.dto.findAllProfile;

import com.redcutlery.thingder.domain.report.profile.entity.ProfileReport;
import lombok.Data;

import java.util.UUID;

@Data
public class ResAdminFindAllProfileElement {
    private UUID uid;
    private UUID memberUid;
    private String message;

    public ResAdminFindAllProfileElement(ProfileReport profileReport) {
        this.uid = profileReport.getUid();
        this.memberUid = profileReport.getMember().getUid();
        this.message = profileReport.getMessage();
    }
}
