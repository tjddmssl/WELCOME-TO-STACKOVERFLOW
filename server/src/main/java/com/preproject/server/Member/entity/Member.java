package com.preproject.server.Member.entity;


import com.preproject.server.Member.data.MemberStatus;
import com.preproject.server.Member.data.MemberType;
import com.preproject.server.baseEntity.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.EnumType.*;
import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Entity
@SuperBuilder
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
    private List<MemberType> roles = new ArrayList<>();

}
