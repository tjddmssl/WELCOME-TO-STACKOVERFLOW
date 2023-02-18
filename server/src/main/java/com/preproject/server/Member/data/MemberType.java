package com.preproject.server.Member.data;

import lombok.Getter;

public enum MemberType {
    ROLE_USER(1,"일반 회원") , ROLE_ADMIN(2,"관리자");

    @Getter
    private int index;

    @Getter
    private String description;

    MemberType(int index, String description) {
        this.index = index;
        this.description = description;
    }
}
