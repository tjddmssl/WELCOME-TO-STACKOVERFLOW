package com.preproject.server.vote.entity;

import com.preproject.server.answer.entity.Answer;
import com.preproject.server.member.entity.Member;
import com.preproject.server.question.entity.Question;
import lombok.*;

import javax.persistence.*;
import org.springframework.lang.Nullable;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Vote {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "Vote_Id")
  private Long id;

  @Enumerated(EnumType.STRING)
  private status status;  // 좋아요 +1 / 싫어요 -1

  //연관관계
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "Member_Id")
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "Question_Id")
  @Nullable
  private Question question;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "Answer_Id")
  @Nullable
  private Answer answer;

  public enum status {
    PLUS(1),
    MINUS(-1);

    @Getter
    private final int num;

    status(int num) {
      this.num = num;
    }
  }
}
