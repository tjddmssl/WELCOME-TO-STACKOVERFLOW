package com.preproject.server.member.dto;

import com.preproject.server.member.data.MemberStatus;
import com.preproject.server.member.data.MemberType;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class MemberPostResponseDto {
    private Long id;
    private String email;
    private String displayName;
    private String password;
    private String profile;
    private String location;
    private String aboutMe;
    private MemberStatus memberStatus;
    private MemberType memberType;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

}
