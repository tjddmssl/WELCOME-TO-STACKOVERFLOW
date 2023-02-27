package com.preproject.server.member.entity;


import com.preproject.server.answer.entity.Answer;
import com.preproject.server.baseEntity.BaseEntity;
import com.preproject.server.comment.entity.Comment;
import com.preproject.server.member.data.MemberStatus;
import com.preproject.server.member.data.MemberType;
import com.preproject.server.question.entity.Question;
import com.preproject.server.tag.entity.TagMember;
import com.preproject.server.vote.entity.Vote;
import lombok.*;
import lombok.Builder.Default;
import lombok.ToString.Exclude;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;
import static lombok.AccessLevel.PROTECTED;


@Entity
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Getter
@ToString
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "Member_Id")
    private Long id;
    private String email;

    @Setter
    private String displayName;
    @Setter
    private String password;
    @Setter
    private String profile;
    @Setter
    private String location;
    @Setter
    private String aboutMe;
    @Enumerated(STRING)
    @Default
    @Setter
    private MemberStatus memberStatus = MemberStatus.MEMBER_ACTIVE;

    @ElementCollection(fetch = EAGER)
    @Setter
    private List<String> roles = new ArrayList<>();

    @Setter
    @Builder.Default
    private String provider = "JWT";    //어떤 OAuth 사용했는지 체크
    @Setter
    private String provideId ;   //해당 OAuth 의 key(id)

    public Member(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.displayName = member.getDisplayName();
        this.password = member.getPassword();
    }

    @Enumerated(STRING)
    private MemberType memberType;

    //연관관계
    @OneToMany(mappedBy = "member")
    @Default
    @Exclude
    private List<Answer> answers = new ArrayList<>();
    @OneToMany(mappedBy = "member")
    @Default
    @Exclude
    private List<Comment> comments = new ArrayList<>();
    @OneToMany(mappedBy = "member")
    @Default
    @Exclude
    private List<Vote> votes = new ArrayList<>();
    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL,orphanRemoval = true)
    @Default
    @Exclude
    private Set<TagMember> tagMembers = new LinkedHashSet<>();
    @OneToMany(mappedBy = "member")
    @Default
    @Exclude
    private List<Question> questions = new ArrayList<>();


    //#### 연관관계 편의 메서드 #### //
    public void addTagMember(TagMember tagMember) {
        this.tagMembers.add(tagMember);
        tagMember.setMember(this);
    }
}
