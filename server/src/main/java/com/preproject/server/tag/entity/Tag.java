package com.preproject.server.tag.entity;

import com.preproject.server.baseEntity.BaseEntity;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder.Default;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Tag extends BaseEntity {
    //오디터블 써야하는지?
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Tag_Id")
    private Long id;
    @NotNull
    @Column(name = "Tag_Name")
    private String name;
    @NotNull
    @Column(name = "Tag_Description")
    private String description;

    // 연관관계
    @OneToMany(mappedBy = "tag")
    @Default
    private List<TagMember> tagMembers = new ArrayList<>();

    @OneToMany(mappedBy = "tag")
    @Default
    private List<TagQuestion> tagQuestions = new ArrayList<>();

}
