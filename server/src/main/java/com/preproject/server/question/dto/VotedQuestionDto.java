package com.preproject.server.question.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class VotedQuestionDto {

  private Long id;
  private String title;
  @Setter
  private List<String> tag;
  private LocalDateTime createdDate;

}
