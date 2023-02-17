package com.preproject.server.answer.entity;

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
public class Answer {

  @Id
  @GeneratedValue
  private Long id;
  @Column(nullable = false)
  @Lob
  private String content;

  @Column(nullable = false)
  private Integer voteCount;

  // TODO Question, Member 구현 후
//   @ManyToOne
//   @JoinColumn(name = "ID")
//   private Member member;
//   @ManyToOne
//   @JoinColumn(name = "ID")
//   private Question question;
}
