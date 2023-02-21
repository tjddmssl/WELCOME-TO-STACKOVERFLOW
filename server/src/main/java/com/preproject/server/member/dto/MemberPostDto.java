package com.preproject.server.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberPostDto {
    private String email;
    private String password;
    private String displayName;

}
