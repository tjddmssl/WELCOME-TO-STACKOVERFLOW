package com.preproject.server.tag.entity;

import com.preproject.server.baseEntity.BaseEntity;
import com.preproject.server.question.entity.Question;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
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

}
