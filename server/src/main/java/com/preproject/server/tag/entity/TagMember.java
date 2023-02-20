package com.preproject.server.tag.entity;

import com.preproject.server.Member.entity.Member;
import com.preproject.server.question.entity.Question;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TagMember {
    @Id
    @GeneratedValue
    @Column(name = "Tag_Member_Id")
    private Long id;

    // 연관관계
    @ManyToOne
    @JoinColumn(name = "Member_Id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "Tag_Id")
    private Tag tag;


}
