package com.preproject.server.member.dto;

import com.preproject.server.answer.entity.Answer;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberDetailAnswerDto {

    private Long id;
    private String content;
    private long voteCount;
    private LocalDateTime createdDate;

    public MemberDetailAnswerDto(Answer answer) {
        this.id = answer.getId();
        this.content = answer.getContent();
        this.voteCount = answer.getVoteCount();
        this.createdDate = answer.getCreatedDate();
    }
}

