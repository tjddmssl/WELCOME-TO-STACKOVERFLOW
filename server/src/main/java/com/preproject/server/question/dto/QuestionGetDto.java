package com.preproject.server.question.dto;

import com.preproject.server.Member.entity.Member;
import com.preproject.server.answer.entity.Answer;
import com.preproject.server.comment.entity.Comment;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class QuestionGetDto {
  @Getter
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Response {
    private String title;
    private String content;
    private Long viewCount;
    private Long voteCount;
    private IS_VOTED isVoted;
    private List<Answer> answers;
    private Member member;
    private List<Comment> comments;

    // TODO tag
  }

  public enum IS_VOTED{

    NOT_SIGNED_IN("not signed in"),
    NOT_VOTED("didn't vote"),
    VOTED("voted"),
    ;

    @Getter
    private final String status;
    IS_VOTED(String status) {
      this.status = status;
    }
  }
}
