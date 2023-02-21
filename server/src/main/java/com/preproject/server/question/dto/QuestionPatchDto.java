package com.preproject.server.question.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class QuestionPatchDto {
  private String title;
  private String content;
  private String tag;


}
