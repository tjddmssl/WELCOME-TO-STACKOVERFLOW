package com.preproject.server.question.dto;


import java.util.List;
import lombok.Data;

@Data
public class QuestionPatchDto {
  private Long id;
  private String title;
  private String content;
  private List<String> tag;

}
