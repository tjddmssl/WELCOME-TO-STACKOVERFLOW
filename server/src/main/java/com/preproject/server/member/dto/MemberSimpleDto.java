package com.preproject.server.member.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberSimpleDto {
    private Long id;
    private String displayName;
    private String profileImage;

}
