package com.preproject.server.question.dto;

import com.preproject.server.member.dto.MemberSimpleDto;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
public class QuestionListGetDto {

  private long id;
  private String title;
  private String content;
  private long viewCount;
  private long voteCount;
  @Setter
  private List<String> tags;
  private LocalDateTime createdDate;
  private LocalDateTime lastModifiedDate;
  @Setter
  private MemberSimpleDto member;

}
