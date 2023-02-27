package com.preproject.server.comment.dto;

import lombok.Data;

@Data
public class CommentResponseDto {

  private String content;
  private Long memberId;
  private Long questionId;
  private Long answerId;
}
