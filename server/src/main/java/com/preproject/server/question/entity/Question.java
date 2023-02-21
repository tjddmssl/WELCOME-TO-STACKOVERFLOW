package com.preproject.server.question.entity;

import com.preproject.server.answer.entity.Answer;
import com.preproject.server.baseEntity.BaseEntity;
import com.preproject.server.baseEntity.BaseEntityWithBy;
import com.preproject.server.comment.entity.Comment;
import com.preproject.server.member.entity.Member;
import com.preproject.server.tag.entity.TagQuestion;
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
@ToString
public class Question extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Question_Id")
    private Long id;
    @Column()
    @Setter
    private String title;
    @Lob
    @Setter
    private String content;
    @Builder.Default
    @Setter
    private Long viewCount = 0L;
    @Builder.Default
    @Setter
    private Long voteCount = 0L;

    // 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Member_Id")
    @Setter
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

    @OneToMany(mappedBy = "question")
    @Builder.Default
    @Setter
    private List<TagQuestion> tagQuestions = new ArrayList<>();
}
