package com.preproject.server.question.dao;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberQuestionDao {

  private Long id;
  private String title;
  private List<String> tag;
  private LocalDateTime createdDate;

}
