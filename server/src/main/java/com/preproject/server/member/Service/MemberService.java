package com.preproject.server.member.Service;

import com.preproject.server.auth.utils.CustomAuthorityUtils;
import com.preproject.server.member.entity.Member;
import com.preproject.server.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class MemberService {
  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;
  private final CustomAuthorityUtils authorityUtils;

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

  public Member getMember(long memberId) {
    return memberRepository.findById(memberId).get();
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

  private void verifyExistsEmail(String email) {
    if(memberRepository.findByEmail(email).isPresent())
      throw new RuntimeException("이미 회원이 존재합니다.");
  }
}

