package com.preproject.server.member.dto;

import lombok.Data;

import java.util.List;

@Data
public class MemberGetListDto {
    private Long memberId;
    private String displayName;
    private String profile;
    private List<String> tags;
}
