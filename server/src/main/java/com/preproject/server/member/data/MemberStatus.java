package com.preproject.server.member.data;

import lombok.Getter;

public enum MemberStatus {
    MEMBER_ACTIVE(1,"활동 회원"), MEMBER_DELETE(2,"탈퇴 회원"),MEMBER_SLEEP(3,"휴면 회원");
    @Getter
    private int index;
    @Getter
    private String name;
    MemberStatus(int index, String name) {
        this.index = index;
        this.name = name;
    }
}
