package com.redcutlery.thingder.domain.memberMember.repository;

import com.redcutlery.thingder.domain.member.entity.Member;
import com.redcutlery.thingder.domain.memberMember.entity.MemberMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MemberMemberRepository extends JpaRepository<MemberMember, UUID> {
    Optional<MemberMember> findByPickerAndSubject(Member picker, Member subject);
}