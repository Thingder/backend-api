package com.redcutlery.thingder.domain.report.profile.repository;

import com.redcutlery.thingder.domain.report.profile.entity.ProfileReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfileReportRepository extends JpaRepository<ProfileReport, UUID> {
}