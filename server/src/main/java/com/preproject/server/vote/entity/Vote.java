package com.preproject.server.vote.entity;

import com.preproject.server.Member.entity.Member;
import com.preproject.server.answer.entity.Answer;
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
public class Vote {
    @Id
    @GeneratedValue
    @Column(name = "VOTE_ID")
    private Long id;

    private Integer voteCount;

    //연관관계
    //
    private Member member;
    //
    private Question question;
    //
    private Answer answer;
}
