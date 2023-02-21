package com.preproject.server.member.dto;

import lombok.Data;

import java.util.List;

@Data
public class MemberListDto {
    private Long id;
    private String displayName;
    private String location;
    private List<String> tags;
    private long voteCount;
    //자신이 받은 총 voteCount 수를 나타낸다.
}
