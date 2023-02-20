package com.preproject.server.question.entity;

import com.preproject.server.Member.entity.Member;
import com.preproject.server.answer.entity.Answer;
import com.preproject.server.baseEntity.BaseEntityWithBy;
import com.preproject.server.comment.entity.Comment;
import com.preproject.server.vote.entity.Vote;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Question extends BaseEntityWithBy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Question_Id")
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    @Lob
    private String content;
    @Builder.Default
    private Long viewCount = 0L;
    @Builder.Default
    private Long voteCount = 0L;

    // 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Member_Id")
    private Member member;
    @OneToMany(mappedBy = "question")
    @Builder.Default
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "question")
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "question")
    @Builder.Default
    private List<Vote> votes = new ArrayList<>();
}
