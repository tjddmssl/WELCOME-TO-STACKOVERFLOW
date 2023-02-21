package com.preproject.server.Member.Service;

import com.preproject.server.Member.entity.Member;
import com.preproject.server.Member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public Member getMember(long memberId) {
        return memberRepository.findById(memberId).get();
    }

    public List<Member> getMemberList() {
        return memberRepository.findAll();
    }

    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }


}
