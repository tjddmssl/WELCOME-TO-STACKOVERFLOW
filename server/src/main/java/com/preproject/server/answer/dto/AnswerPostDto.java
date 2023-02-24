package com.preproject.server.answer.dto;

import lombok.Data;

import java.util.List;

@Data
public class AnswerPostDto {
    private Long questionId;
    private String content;
    private Long memberId;
}
