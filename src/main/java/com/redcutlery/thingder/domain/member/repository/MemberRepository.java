package com.redcutlery.thingder.domain.member.repository;

import com.redcutlery.thingder.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {
    boolean existsByEmail(String email);

    Optional<Member> findByEmail(String email);
}
