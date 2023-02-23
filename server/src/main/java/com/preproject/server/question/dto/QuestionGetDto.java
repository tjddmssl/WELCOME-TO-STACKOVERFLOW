package com.preproject.server.question.dto;

import com.preproject.server.comment.dto.CommentSimpleDto;
import com.preproject.server.member.dto.MemberSimpleDto;
import java.util.List;
import lombok.Data;
import lombok.Getter;

@Data
public class QuestionGetDto {

  private String title;
  private String content;
  private Long viewCount;
  private Long voteCount;
  private IS_VOTED isVoted;
  private MemberSimpleDto member;
  private List<CommentSimpleDto> comments;
  private List<String> tags;

  public enum IS_VOTED {

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
