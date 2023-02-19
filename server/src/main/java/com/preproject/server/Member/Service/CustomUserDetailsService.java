package com.preproject.server.Member.Service;

import com.preproject.server.Member.entity.Member;
import com.preproject.server.Member.repository.MemberRepository;
import com.preproject.server.Member.utils.CustomAuthorityUtils;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final CustomAuthorityUtils authorityUtils;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member findMember = memberRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        return CustomUserDetail.builder()
                .id(findMember.getId())
                .displayName(findMember.getDisplayName())
                .password(findMember.getPassword())
                .aboutMe(findMember.getAboutMe())
                .email(findMember.getEmail())
                .profile(findMember.getProfile())
                .location(findMember.getLocation())
                .memberStatus(findMember.getMemberStatus())
                .build();
    }

    //부모 자식 모두 superbuilder 써야 자식 클래스로만들어짐 -> superBuilder 찾아보기
    @SuperBuilder
    private final class CustomUserDetail extends Member implements UserDetails {

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorityUtils.createAuthorities(this.getEmail());
        }

        @Override
        public String getUsername() {
            return this.getEmail();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}