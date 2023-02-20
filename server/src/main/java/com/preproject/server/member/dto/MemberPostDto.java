package com.preproject.server.member.dto;

import lombok.Data;

@Data
public class MemberPostDto {
    private String email;
    private String password;
    private String displayName;

}
