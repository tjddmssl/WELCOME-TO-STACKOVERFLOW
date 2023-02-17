package com.preproject.server.Member.data;

import lombok.Getter;

public enum MemberType {
    MEMBER_USER(1,"일반 회원") , MEMBER_ADMIN(2,"관리ㄴ");

    @Getter
    private int index;

    @Getter
    private String name;

    MemberType(int index, String name) {
        this.index = index;
        this.name = name;
    }
}
