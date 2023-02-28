package com.preproject.server.member.dto;

import java.util.Set;
import lombok.Data;

@Data
public class MemberPatchDto {

  private Long id;
  private String password;
  private String displayName;
  private String profileImage;
  private String aboutMe;
  private Set<String> tag;
}
