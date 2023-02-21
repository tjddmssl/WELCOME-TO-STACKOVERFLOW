package com.preproject.server.member.Service;

import com.preproject.server.member.entity.Member;
import com.preproject.server.member.repository.MemberRepository;
import com.preproject.server.member.utils.CustomAuthorityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthorityUtils authorityUtils;

    /*
     * 회원 가입
     * 권한 부여 및 비밀 번호 압호화
     * */
    @Override
    public Member createMember(Member member) {
        log.info("member = {}", member);
        verifyExistsEmail(member.getEmail());
        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encryptedPassword);
        List<String> roles = authorityUtils.createRoles(member.getEmail());
        member.setRoles(roles);
        log.info("member encryptedPassword = {}", encryptedPassword);
        return memberRepository.save(member);
    }

    /*
     * Member 단건 조회
     * */
    @Override
    public Member getMember(long memberId) {
        return memberRepository.findById(memberId).get();
    }

    /*
     * MemberList 조회
     * */
    @Override
    public List<Member> getMemberList() {
        return memberRepository.findAll();
    }

    /*
     * Member 삭제
     * */
    @Override
    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }

    /*
     * email 이미 존재하는지 검증
     * */
    private Member verifyExistsEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("email이 존재합니다."));
    }
}
