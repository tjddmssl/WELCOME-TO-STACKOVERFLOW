package com.preproject.server.tag.entity;

import com.preproject.server.baseEntity.BaseEntity;
import com.preproject.server.exception.BusinessLogicException;
import com.preproject.server.question.entity.Question;
import com.preproject.server.tag.exception.TagExceptionCode;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "tag_question")
public class TagQuestion {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "Tag_Question_Id")
  private Long id;

  // 연관관계
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "Question_Id")
  private Question question;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "Tag_Id")
  private Tag tag;

  public void addQuestion(Question question){
    this.question = question;
    if(this.question.getTagQuestions().contains(this)) throw new BusinessLogicException(
        TagExceptionCode.TAG_ALREADY_ADDED);
    else this.question.getTagQuestions().add(this);
  }

  public void addTag(Tag tag) {
    this.tag = tag;
    if(this.tag.getTagQuestions().contains(this)) throw new BusinessLogicException(TagExceptionCode.TAG_ALREADY_ADDED);
    else this.tag.getTagQuestions().add(this);
  }
}
