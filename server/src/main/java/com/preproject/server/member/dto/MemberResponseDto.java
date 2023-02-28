package com.preproject.server.member.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberResponseDto {

  private Long id;
  private String email;
  private String password;
  private String displayName;
  private String profile;
  private String aboutMe;
  private String location;
  private LocalDateTime createdDate;
  private LocalDateTime lastModifiedDate;
  private List<String> tags = new ArrayList<>();


}
//
