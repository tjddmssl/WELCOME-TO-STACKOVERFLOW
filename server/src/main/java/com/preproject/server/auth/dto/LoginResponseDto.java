package com.preproject.server.auth.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDto {
    private Long memberId;
    private String displayName;

}
