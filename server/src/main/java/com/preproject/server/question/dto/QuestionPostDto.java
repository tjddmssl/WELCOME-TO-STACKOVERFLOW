package com.preproject.server.question.dto;

import com.preproject.server.Member.entity.Member;
import com.preproject.server.answer.entity.Answer;
import com.preproject.server.comment.entity.Comment;
import com.preproject.server.tag.entity.TagQuestion;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
public class QuestionPostDto {

  @NotBlank
  private String title;
  @NotBlank
  private String content;
  private List<String> Tag;
  @Positive
  private Long memberId;

}
