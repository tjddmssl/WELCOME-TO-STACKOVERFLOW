package com.preproject.server.answer.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerResponseDto {

  private Long answerId;
  private Long questionId;
  private Long memberId;
  private String content;
  private LocalDateTime createdDate;
  private LocalDateTime lastModifiedDate;

}
