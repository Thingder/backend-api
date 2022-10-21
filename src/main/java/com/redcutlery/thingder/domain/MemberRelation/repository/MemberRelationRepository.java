package com.redcutlery.thingder.domain.MemberRelation.repository;

import com.redcutlery.thingder.domain.MemberRelation.entity.MemberRelation;
import com.redcutlery.thingder.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MemberRelationRepository extends JpaRepository<MemberRelation, UUID> {
    Optional<MemberRelation> findByMemberAndTarget(Member member, Member target);
}