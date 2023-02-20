package com.preproject.server.Member.Service;

import com.preproject.server.Member.entity.Member;
import com.preproject.server.Member.utils.CustomAuthorityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

//부모 자식 모두 superbuilder 써야 자식 클래스로만들어짐 -> superBuilder 찾아보기
//TODO Builder 직접 구혀하기
@RequiredArgsConstructor
public class CustomUserDetail extends Member implements UserDetails {
    private final CustomAuthorityUtils authorityUtils;

    public CustomUserDetail(Member member, CustomAuthorityUtils authorityUtils) {
        this.authorityUtils = authorityUtils;

    }

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
        return true;
    }
    //TODO Builder 패턴 직접 구현
}