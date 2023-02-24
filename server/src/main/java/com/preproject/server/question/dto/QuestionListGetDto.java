package com.preproject.server.question.dto;

import com.preproject.server.member.dto.MemberSimpleDto;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class QuestionListGetDto {

  private long id;
  private String title;
  private String content;
  private List<String> tags;
  private LocalDateTime createdDate;
  private LocalDateTime lastModifiedDate;
  private List<MemberSimpleDto> members;

}
