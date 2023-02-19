package com.preproject.server.tag.entity;

import com.preproject.server.baseEntity.BaseEntity;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Tag extends BaseEntity {
    //오디터블 써야하는지?
    @Id
    @GeneratedValue
    @Column(name = "TAG_ID")
    private Long id;
    @NotNull
    @Column(name = "TAG_NAME")
    private String name;
    @NotNull
    @Column(name = "TAG_DESCRIBTION")
    private String describtion;

}
