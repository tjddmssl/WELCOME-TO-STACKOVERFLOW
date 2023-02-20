package com.preproject.server.member.dto;

import lombok.Data;

import java.util.List;

@Data
public class MemberPatchDto {
    private String password;
    private String displayName;
    private String profile;
    private String aboutMe;
    private List<String> tag;
}
