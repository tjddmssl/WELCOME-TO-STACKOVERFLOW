package com.preproject.server.member.dto;

import lombok.Data;

import java.util.Set;

@Data
public class MemberPatchDto {
    private String password;
    private String displayName;
    private String profileImage;
    private String aboutMe;
    private Set<String> tag;
}
