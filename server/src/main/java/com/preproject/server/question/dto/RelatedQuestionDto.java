package com.preproject.server.question.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class RelatedQuestionDto {

  private Long id;
  private String title;
}
