package com.preproject.server.tag.entity;

import com.preproject.server.Member.entity.Member;
import com.preproject.server.baseEntity.BaseEntity;
import com.preproject.server.question.entity.Question;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TagQuestion {
    @Id
    @GeneratedValue
    @Column(name = "TAG_QUESTION_ID")
    private Long id;

    //
    private Question question;
    //
    private Tag tag;


}
