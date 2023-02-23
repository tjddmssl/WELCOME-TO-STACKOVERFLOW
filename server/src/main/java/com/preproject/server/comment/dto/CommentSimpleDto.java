package com.preproject.server.comment.dto;

import com.preproject.server.member.dto.MemberSimpleDto;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class CommentSimpleDto {

  private String content;
  private LocalDateTime createdDate;
  private MemberSimpleDto member;
}
