package com.preproject.server.member.Service;

import com.preproject.server.member.entity.Member;
import com.preproject.server.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    public void deleteMember(Long memberId,String password) {
        Member findMember = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("회원이 존재하지 않습닌다."));

        if (findMember.getPassword().equals(password)) {
            memberRepository.deleteById(memberId);
        } else {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
    }

    //TODO 기본적인 update 구도 설정
    public Member updatedMember(Member member, List<String> tagMember) {

        return member;
    }


    public Page<Member> getPageMember() {

        return null;

    }
}
