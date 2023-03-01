package com.preproject.server.question.dto;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class QuestionPostDto {
  private String id;
  @NotBlank
  private String title;
  @NotBlank
  private String content;
  @NotNull
  private List<String> tags;

}
