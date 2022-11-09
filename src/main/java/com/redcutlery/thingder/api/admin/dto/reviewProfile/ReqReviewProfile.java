package com.redcutlery.thingder.api.admin.dto.reviewProfile;

import com.redcutlery.thingder.domain.report.profile.entity.ProfileReport;
import lombok.Data;

@Data
public class ReqReviewProfile {
    private ProfileReport.Status status;
}
