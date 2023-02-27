package com.preproject.server.member.repository;

import com.preproject.server.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m where m.email = :email and m.memberStatus = 'MEMBER_ACTIVE'")
    Optional<Member> findByEmailMemberActive(String email);

    Optional<Member> findByEmail(String email);
    Page<Member> findAll(Pageable pageable);
}
