package com.preproject.server.question.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class QuestionPostDto {
  private long id;
  @NotBlank
  private String title;
  @NotBlank
  private String content;
  @NotNull
  private List<String> tag;

}
