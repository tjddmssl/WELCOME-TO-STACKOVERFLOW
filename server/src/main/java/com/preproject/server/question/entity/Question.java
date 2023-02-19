package com.preproject.server.question.entity;

import com.preproject.server.baseEntity.BaseEntity;
import com.preproject.server.baseEntity.BaseEntityWithBy;
import lombok.*;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Question extends BaseEntityWithBy {
    @Id
    @GeneratedValue
    @Column(name = "QUESTION_ID")
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    private Integer viewCount;
    private Integer voteCount;

    //
    //private Member member;

}
