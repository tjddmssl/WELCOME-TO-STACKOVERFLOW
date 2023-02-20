package com.preproject.server.comment.entity;

import com.preproject.server.Member.entity.Member;
import com.preproject.server.answer.entity.Answer;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.preproject.server.question.entity.Question;
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
  @Column(name = "Comment_Id")
  private Long id;
  @Column(nullable = false)
  @Lob
  private String content;

  // 연관관계
  @ManyToOne
  @JoinColumn(name = "Member_Id")
  private Member member;
  @ManyToOne
  @JoinColumn(name = "Question_Id")
  private Question question;
  @ManyToOne
  @JoinColumn(name = "Answer_Id")
  private Answer answer;
}
