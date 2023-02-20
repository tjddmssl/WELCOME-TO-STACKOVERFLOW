package com.preproject.server.member.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MemberPatchResponseDto {
    private Long id;
    private String password;
    private String displayName;
    private String profile;
    private String aboutMe;
    private List<String> tag;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

}

