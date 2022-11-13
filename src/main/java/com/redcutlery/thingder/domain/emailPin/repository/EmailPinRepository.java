package com.redcutlery.thingder.domain.emailPin.repository;

import com.redcutlery.thingder.domain.emailPin.entity.EmailPin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmailPinRepository extends JpaRepository<EmailPin, UUID> {
    Optional<EmailPin> findByEmail(String email);
}