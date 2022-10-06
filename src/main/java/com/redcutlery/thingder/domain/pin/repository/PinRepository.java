package com.redcutlery.thingder.domain.pin.repository;

import com.redcutlery.thingder.domain.pin.entity.Pin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PinRepository extends JpaRepository<Pin, UUID> {
    Optional<Pin> findByPhone(String pin);
}