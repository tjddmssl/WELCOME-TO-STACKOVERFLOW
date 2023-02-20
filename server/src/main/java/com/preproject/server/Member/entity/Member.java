package com.preproject.server.Member.entity;


import com.preproject.server.Member.data.MemberStatus;
import com.preproject.server.Member.data.MemberType;
import com.preproject.server.answer.entity.Answer;
import com.preproject.server.baseEntity.BaseEntity;
import com.preproject.server.comment.entity.Comment;
import com.preproject.server.tag.entity.TagMember;
import com.preproject.server.tag.entity.TagQuestion;
import com.preproject.server.vote.entity.Vote;
import lombok.*;
import lombok.Builder.Default;
import lombok.ToString.Exclude;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.EnumType.*;
import static lombok.AccessLevel.*;

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
    private String displayName;
    private String password;
    private String profile;
    private String location;
    private String aboutMe;
    @Enumerated(STRING)
    private MemberStatus memberStatus;
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
    @OneToMany(mappedBy = "member")
    @Default
    @Exclude
    private List<TagMember> tagMembers = new ArrayList<>();
    @OneToMany(mappedBy = "member")
    @Default
    @Exclude
    private List<TagQuestion> tagQuestions = new ArrayList<>();


}
