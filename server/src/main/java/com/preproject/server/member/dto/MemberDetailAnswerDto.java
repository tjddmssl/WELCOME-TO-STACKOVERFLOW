package com.preproject.server.member.dto;

import com.preproject.server.answer.entity.Answer;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class MemberDetailAnswerDto {

  private Long id;
  private String content;
  private long voteCount;
  private LocalDateTime createdDate;

  public MemberDetailAnswerDto(Answer answer) {
    this.id = answer.getId();
    this.content = answer.getContent();
    this.voteCount = answer.getVoteCount();
    this.createdDate = answer.getCreatedDate();
  }

  public MemberDetailAnswerDto(Long id, String content, long voteCount, LocalDateTime createdDate) {
    this.id = id;
    this.content = content;
    this.voteCount = voteCount;
    this.createdDate = createdDate;
  }
}

