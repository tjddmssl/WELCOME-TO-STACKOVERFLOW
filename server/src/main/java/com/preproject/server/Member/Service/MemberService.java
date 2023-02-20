package com.preproject.server.Member.Service;

import com.preproject.server.Member.entity.Member;

import java.util.List;


public interface MemberService {
    Member createMember(Member member);

    Member getMember(long memberId);

    List<Member> getMemberList();

    void deleteMember(Long memberId);
}
