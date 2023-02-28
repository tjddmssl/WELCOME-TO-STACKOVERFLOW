package com.preproject.server.answer.dto;

import lombok.Data;

import java.util.List;
import lombok.Setter;

@Data
public class AnswerPostDto {
    @Setter
    private long questionId;
    private String content;
    private Long memberId;
}
