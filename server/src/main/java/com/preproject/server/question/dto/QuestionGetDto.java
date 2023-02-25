package com.preproject.server.question.dto;

import com.preproject.server.comment.dto.CommentSimpleDto;
import com.preproject.server.member.dto.MemberSimpleDto;
import com.preproject.server.vote.IS_VOTED;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Builder
@AllArgsConstructor
public class QuestionGetDto {

  private Long id;
  private String title;
  private String content;
  private Long viewCount;
  private Long voteCount;
  @Setter
  private IS_VOTED isVoted;
  private MemberSimpleDto member;
  private List<CommentSimpleDto> comments;
  @Setter
  private List<String> tag;
  private LocalDateTime createdDate;
  private LocalDateTime lastModifiedDate;


}
