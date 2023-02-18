package com.preproject.server.comment.entity;

import com.preproject.server.answer.entity.Answer;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
  @Id
  @GeneratedValue
  @Column(name = "COMMENT_ID")
  private Long id;
  @Column(nullable = false)
  @Lob
  private String content;

  // TODO Question, Member 구현 후
//   @ManyToOne
//   @JoinColumn(name = "ID")
//   private Member member;
//   @ManyToOne
//   @JoinColumn(name = "ID")
//   private Question question;
  @ManyToOne
  @JoinColumn(name = "ANSWER_ID")
  private Answer answer;
}
