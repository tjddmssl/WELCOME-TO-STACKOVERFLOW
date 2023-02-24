package com.preproject.server.comment.dao;

import com.preproject.server.member.dto.MemberSimpleDto;
import com.preproject.server.member.entity.Member;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentSimpleDao {

  private String content;
  private LocalDateTime createdDate;
  private Member member;
}
