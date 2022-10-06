package com.redcutlery.thingder.domain.image.repository;

import com.redcutlery.thingder.domain.image.entity.ImageFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ImageFileRepository extends JpaRepository<ImageFile, UUID> {
    Optional<ImageFile> findBySha256(String sha256);
}