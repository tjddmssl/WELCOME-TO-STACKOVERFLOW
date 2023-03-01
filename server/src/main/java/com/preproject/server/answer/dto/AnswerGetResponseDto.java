package com.preproject.server.answer.dto;

import com.preproject.server.comment.dto.CommentSimpleDto;
import com.preproject.server.member.dto.MemberSimpleDto;
import com.preproject.server.vote.IS_VOTED;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerGetResponseDto {

  private Long id;
  private Long questionId;
  private Long voteCount;
  private String content;
  private IS_VOTED isVoted;
  private LocalDateTime createdDate;
  private LocalDateTime lastModifiedDate;
  private MemberSimpleDto member;
  private List<CommentSimpleDto> comments;
}
