package com.preproject.server.question.dto;


import lombok.Data;

import java.util.List;

@Data
public class QuestionPatchDto {

  private Long id;
  private String title;
  private String content;
  private List<String> tags ;

}
