package com.preproject.server.question.dto;

import com.preproject.server.answer.entity.Answer;
import com.preproject.server.comment.entity.Comment;
import com.preproject.server.member.entity.Member;
import com.preproject.server.tag.dto.TagQuestionDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
public class QuestionGetDto {
    private String title;
    private String content;
    private Long viewCount;
    private Long voteCount;
    private IS_VOTED isVoted;
    private List<Answer> answers;
    private Member member;
    private List<Comment> comments;
    private List<String> tags;
    // TODO tag
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
