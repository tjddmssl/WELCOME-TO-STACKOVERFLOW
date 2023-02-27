package com.preproject.server.question.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class MemberQuestionDto {

  private long id;
  private String title;
  private String content;
  private long viewCount;
  private long voteCount;
  @Setter
  private List<String> tag;
  private LocalDateTime createdDate;
  private LocalDateTime lastModifiedDate;
}
