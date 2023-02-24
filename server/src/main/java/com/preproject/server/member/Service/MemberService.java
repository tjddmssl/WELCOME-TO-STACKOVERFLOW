package com.preproject.server.member.Service;

import com.preproject.server.auth.utils.CustomAuthorityUtils;
import com.preproject.server.exception.BusinessLogicException;
import com.preproject.server.member.entity.Member;
import com.preproject.server.member.exception.MemberExceptionCode;
import com.preproject.server.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

  private String password;


  public void deleteMember(Long memberId,String password) {
    Member findMember = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("회원이 존재하지 않습닌다."));

    if (findMember.getPassword().equals(password)) {
      memberRepository.deleteById(memberId);
    } else {
      throw new RuntimeException("비밀번호가 일치하지 않습니다.");
    }
  }
  private String displayName;
  private String profileImage;
  private String aboutMe;
  private List<String> tag;

  public Member getMember(long memberId) {
    return memberRepository.findById(memberId).orElseThrow(()->new BusinessLogicException(MemberExceptionCode.MEMBER_NOT_FOUND));
  }

  //TODO 기본적인 update 구도 설정
  public Member updatedMember(Member member, Set<String> tagMember) {
    Member savedMember = checkMemberExist(member.getId());
    //검증 성공
    Optional.ofNullable(member.getPassword()).ifPresent(savedMember::setPassword);
    Optional.ofNullable(member.getDisplayName()).ifPresent(savedMember::setDisplayName);
    Optional.ofNullable(member.getProfile()).ifPresent(savedMember::setProfile);
    Optional.ofNullable(member.getAboutMe()).ifPresent(savedMember::setAboutMe);

    if (!tagMember.isEmpty()) {

    }
    return member;
  }


  public Page<Member> getPageMember(Pageable pageable) {
    Page<Member> all = memberRepository.findAll(pageable);
    return !all.isEmpty() ? all : null;
  }
  /*
  * 회원이 존재 하면 예외 발생
  * */
  private void verifyExistsEmail(String email) {
    if(memberRepository.findByEmail(email).isPresent())
      throw new BusinessLogicException(MemberExceptionCode.MEMBER_EXIST);
  }

  /*
   * 회원이 없으명 예외 발생
   * */
  private Member checkMemberExist(Long memberId) {
    return memberRepository.findById(memberId).orElseThrow(()->new BusinessLogicException(MemberExceptionCode.MEMBER_NOT_FOUND));
  }
}

