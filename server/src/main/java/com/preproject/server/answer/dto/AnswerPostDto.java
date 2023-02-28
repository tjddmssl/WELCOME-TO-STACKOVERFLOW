package com.preproject.server.answer.dto;

import lombok.Data;

@Data
public class AnswerPostDto {

  private long questionId;
  private String content;
}
