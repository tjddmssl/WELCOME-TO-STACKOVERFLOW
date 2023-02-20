package com.preproject.server.vote.entity;

import com.preproject.server.Member.entity.Member;
import com.preproject.server.answer.entity.Answer;
import com.preproject.server.question.entity.Question;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Vote {
    @Id
    @GeneratedValue
    @Column(name = "Vote_Id")
    private Long id;

    private Integer voteCount;

    //연관관계
    @ManyToOne
    @JoinColumn(name = "Member_Id")
    private Member member;

    //Nullable 해줘야하는지?
    @ManyToOne
    @JoinColumn(name = "Question_Id")
    private Question question;
    @ManyToOne
    @JoinColumn(name = "Answer_Id")
    private Answer answer;
}
