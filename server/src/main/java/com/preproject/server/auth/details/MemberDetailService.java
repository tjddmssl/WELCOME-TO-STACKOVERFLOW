package com.preproject.server.auth.details;

import com.preproject.server.auth.utils.CustomAuthorityUtils;
import com.preproject.server.member.entity.Member;
import com.preproject.server.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class MemberDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final CustomAuthorityUtils authorityUtils;

    /*
    * 시큐리티 session -> Authentication -> USerDetails
    * 반환된 값이 Authentication 안에 들어감
    * 함수 종료 이후 @AuthenticationPrincipal 어노테이션 생성된다.
    * */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("#### DB에 있는 DATA와 검증 시작!");
        Member findMember = memberRepository.findByEmailMemberActive(username).orElseThrow(() ->
        {

            log.error("#### error ####");
            return new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        });
        log.info("#### findMemberId = {}", findMember.getId());
        return new PrincipalDetails(findMember,authorityUtils);
    }
}