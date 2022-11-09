package com.redcutlery.thingder.domain.report.chat.repository;

import com.redcutlery.thingder.domain.report.chat.entity.ChatReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatReportRepository extends JpaRepository<ChatReport, UUID> {
}