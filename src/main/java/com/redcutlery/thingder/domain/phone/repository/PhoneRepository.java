package com.redcutlery.thingder.domain.phone.repository;

import com.redcutlery.thingder.domain.phone.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhoneRepository extends JpaRepository<Phone, UUID> {
}
