package com.preproject.server.member.dto;

import com.preproject.server.member.data.MemberStatus;
import java.time.LocalDateTime;
import lombok.Data;


@Data
public class MemberPostResponseDto {

  private Long id;
  private String email;
  private String displayName;
  private String password;
  private String profileImage;
  private String location;
  private String aboutMe;
  private MemberStatus memberStatus;
  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;

}
