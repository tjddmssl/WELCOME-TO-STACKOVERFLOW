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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Vote_Id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private status status;  // 좋아요 +1 / 싫어요 -1

    //연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Member_Id")
    private Member member;

    //Nullable 해줘야하는지?
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Question_Id")
    private Question question;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Answer_Id")
    private Answer answer;

    public enum status {
        VOTE_PLUS(1),
        VOTE_MINUS(-1);

        @Getter
        private final int num;

        status(int num) {
            this.num = num;
        }
    }
}
