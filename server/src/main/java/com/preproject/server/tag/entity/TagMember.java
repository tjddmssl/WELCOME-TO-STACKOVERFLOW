package com.preproject.server.tag.entity;

import com.preproject.server.member.entity.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TagMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Tag_Member_Id")
    private Long id;

    // 연관관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Member_Id")
    @Setter
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Tag_Id")
    private Tag tag;

    // ### 연관관계 편의 메서드 ### //
    public void addMember(Member member){
        this.member = member;
        this.member.getTagMembers().add(this);
        this.tag.getTagMembers().add(this);
    }

}
