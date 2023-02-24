package com.preproject.server.auth.details;

import com.preproject.server.auth.utils.CustomAuthorityUtils;
import com.preproject.server.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Map;

//부모 자식 모두 superbuilder 써야 자식 클래스로만들어짐 -> superBuilder 찾아보기
//TODO Builder 직접 구혀하기
@RequiredArgsConstructor
@ToString
//public class PrincipalDetails extends Member implements UserDetails , OAuth2User {
public class PrincipalDetails extends Member implements UserDetails{
    private final CustomAuthorityUtils authorityUtils;

    //OAuth2
    private Map<String, Object> attributes;

    //일반 로그인 생성자
    public PrincipalDetails(Member member, CustomAuthorityUtils authorityUtils) {
        super(member);
        this.authorityUtils = authorityUtils;
    }

    //OAuth 로그인 생성자


    public PrincipalDetails(Member member, CustomAuthorityUtils authorityUtils, Map<String, Object> attributes) {
        super(member);
        this.authorityUtils = authorityUtils;
        this.attributes = attributes;
    }

//    @Override
//    public Map<String, Object> getAttributes() {
//        return attributes;
//    }

    //권한을 체크할 때 확인되는 인터페이스 SecurityFilterChain 에서 권한 체크에 사용
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityUtils.createAuthorities(this.getRoles());
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
     /*
     * 특젗 조건이 맞으면휴면 계정으로 변경하는 등의 로직이 들어간다. -> 마지막 로그인 이후 1년 뒤에 return fqlse 로 바꾼다 등등
     * */

        return true;
    }

//    @Override
//    public String getName() {
//        return null;
//    }
    //TODO Builder 패턴 직접 구현
}