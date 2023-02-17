package com.preproject.server.Member.entity;


import com.preproject.server.Member.data.MemberStatus;
import com.preproject.server.Member.data.MemberType;
import com.preproject.server.baseEntity.BaseEntity;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.EnumType.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Member extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String email;
    private String nickName;
    private String password;
    private String profile;
    private String location;
    private String aboutMe;
    @Enumerated(STRING)
    private MemberStatus memberStatus;
    @Enumerated(STRING)
    private MemberType memberType;

}
