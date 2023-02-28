package com.preproject.server.member.dto;

import com.preproject.server.question.entity.Question;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class MemberDetailQuestionDto {

  private Long id;
  private String title;
  private List<String> tags;
  private long votedCount;
  private long viewCount;
  private LocalDateTime createdDate;

  public MemberDetailQuestionDto(Question question) {
    this.id = question.getId();
    this.title = question.getTitle();
    this.tags = question.getTagQuestions().stream().map(tag -> tag.getTag().getName())
        .collect(Collectors.toList());
    this.votedCount = question.getVoteCount();
    this.viewCount = question.getViewCount();
    this.createdDate = question.getCreatedDate();
  }

  public MemberDetailQuestionDto(Long id, String title, List<String> tags, long votedCount,
      long viewCount, LocalDateTime createdDate) {
    this.id = id;
    this.title = title;
    this.tags = tags;
    this.votedCount = votedCount;
    this.viewCount = viewCount;
    this.createdDate = createdDate;
  }
}
