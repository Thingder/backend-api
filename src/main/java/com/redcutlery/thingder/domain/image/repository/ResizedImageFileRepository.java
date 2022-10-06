package com.redcutlery.thingder.domain.image.repository;

import com.redcutlery.thingder.domain.image.entity.ResizedImageFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ResizedImageFileRepository extends JpaRepository<ResizedImageFile, UUID> {
}