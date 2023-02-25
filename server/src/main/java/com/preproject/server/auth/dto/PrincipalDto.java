package com.preproject.server.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PrincipalDto {
    private Long id;
    private String displayName;
    private String email;

    @Builder
    public PrincipalDto(Long id, String displayName, String email) {
        this.id = id;
        this.displayName = displayName;
        this.email = email;
    }
}
