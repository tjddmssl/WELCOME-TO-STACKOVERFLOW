package com.preproject.server.Member.entity;


import com.preproject.server.Member.data.MemberStatus;
import com.preproject.server.Member.data.MemberType;
import com.preproject.server.baseEntity.BaseEntity;
import lombok.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;

import static javax.persistence.EnumType.*;
import static lombok.AccessLevel.*;

@Entity
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Getter
@ToString
@CrossOrigin()
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
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

}
