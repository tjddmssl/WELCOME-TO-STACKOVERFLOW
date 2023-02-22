package com.preproject.server.member.entity;


import com.preproject.server.baseEntity.BaseEntity;
import com.preproject.server.member.data.MemberStatus;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @Column(name = "member_id")
    private Long id;
    private String email;
    private String displayName;
    @Setter
    private String password;
    private String profile;
    private String location;
    private String aboutMe;
    @Enumerated(STRING)
    private MemberStatus memberStatus;
    @ElementCollection(fetch = EAGER)
    @Setter
    private List<String> roles = new ArrayList<>();

    private String provider;    //어떤 OAuth 사용했는지 체크
    private String provideId;   //해당 OAuth 의 key(id)
}
