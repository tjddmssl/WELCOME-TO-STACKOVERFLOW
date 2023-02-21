package com.preproject.server.question.dto;

import com.preproject.server.Member.entity.Member;
import javax.validation.constraints.Positive;
import lombok.Getter;

public class QuestionDto {

  @Getter
  public static class Post {

    private String title;
    private String content;
    @Positive
    private Long memberId;

    public Member getMember() {
      return Member.builder().id(this.memberId).build();
    }
  }

}
