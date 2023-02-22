package com.preproject.server.question.dto;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class QuestionPostDto {
  private String id;
  @NotBlank
  private String title;
  @NotBlank
  private String content;
  @NotNull
  private List<String> tags;
  @Positive
  private Long memberId;

}
