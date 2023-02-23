package com.preproject.server.answer.dto;

import lombok.Data;

import java.util.List;
@Data
public class AnswerPatchDto {
    private Long questionId;
    private String content;
    private List<String> tags;
    private Long memberId;
}
