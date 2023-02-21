package com.preproject.server.member.Service;

import com.preproject.server.member.entity.Member;

import java.util.List;


public interface MemberService {
    Member createMember(Member member);

    Member getMember(long memberId);

    List<Member> getMemberList();

    void deleteMember(Long memberId);
}
